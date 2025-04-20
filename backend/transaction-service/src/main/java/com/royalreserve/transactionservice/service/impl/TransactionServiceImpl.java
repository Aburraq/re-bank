package com.royalreserve.transactionservice.service.impl;

import com.royalreserve.transactionservice.dto.BalanceRequest;
import com.royalreserve.transactionservice.dto.TransactionRequest;
import com.royalreserve.transactionservice.dto.TransactionResponse;
import com.royalreserve.transactionservice.exception.TransactionNotFoundException;
import com.royalreserve.transactionservice.model.Transaction;
import com.royalreserve.transactionservice.repository.TransactionRepository;
import com.royalreserve.transactionservice.service.TransactionService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, TransactionResponse> kafkaTemplate;

    public TransactionServiceImpl(TransactionRepository repository,
                                  RestTemplate restTemplate,
                                  KafkaTemplate<String, TransactionResponse> kafkaTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @CircuitBreaker(name = "accountService", fallbackMethod = "processTransactionFallback")
    public TransactionResponse processTransaction(TransactionRequest request) {
        BalanceRequest balanceRequest = new BalanceRequest(request.getAmount());
        String type = request.getType().toLowerCase();
        ResponseEntity<Void> response = restTemplate.postForEntity(
                "http://account-service/api/accounts/{id}/" + type,
                balanceRequest,
                Void.class,
                request.getAccountId()
        );
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account service error");
        }

        Transaction tx = new Transaction(request.getAccountId(), request.getType(), request.getAmount(), LocalDateTime.now());
        Transaction saved = repository.save(tx);

        TransactionResponse txResp = new TransactionResponse(saved.getId(), saved.getAccountId(), saved.getType(), saved.getAmount(), saved.getCreatedAt());
        kafkaTemplate.send("transactions", txResp);

        return txResp;
    }

    public TransactionResponse processTransactionFallback(TransactionRequest request, Throwable t) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Account service unavailable");
    }

    @Override
    public TransactionResponse getTransactionById(Long id) {
        Transaction tx = repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        return toResponse(tx);
    }

    @Override
    public List<TransactionResponse> getAllTransactions() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    private TransactionResponse toResponse(Transaction tx) {
        return new TransactionResponse(tx.getId(), tx.getAccountId(), tx.getType(), tx.getAmount(), tx.getCreatedAt());
    }
}
