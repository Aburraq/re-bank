package com.royalreserve.transactionservice.service;

import com.royalreserve.transactionservice.dto.BalanceRequest;
import com.royalreserve.transactionservice.dto.TransactionRequest;
import com.royalreserve.transactionservice.dto.TransactionResponse;
import com.royalreserve.transactionservice.exception.TransactionNotFoundException;
import com.royalreserve.transactionservice.model.Transaction;
import com.royalreserve.transactionservice.repository.TransactionRepository;
import com.royalreserve.transactionservice.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {
    @Mock
    private TransactionRepository repository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private KafkaTemplate<String, TransactionResponse> kafkaTemplate;

    private TransactionServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TransactionServiceImpl(repository, restTemplate, kafkaTemplate);
    }

    @Test
    void processTransaction_success() {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId("acc1");
        request.setType("DEPOSIT");
        request.setAmount(new BigDecimal("100.00"));

        when(restTemplate.postForEntity(eq("http://account-service/api/accounts/{id}/deposit"),
                any(BalanceRequest.class), eq(Void.class), eq("acc1")))
            .thenReturn(ResponseEntity.ok().build());

        Transaction saved = new Transaction("acc1", "DEPOSIT", new BigDecimal("100.00"), LocalDateTime.now());
        saved.setId(1L);
        when(repository.save(any(Transaction.class))).thenReturn(saved);

        TransactionResponse resp = service.processTransaction(request);
        assertEquals(1L, resp.getId());
        assertEquals("acc1", resp.getAccountId());
        assertEquals("DEPOSIT", resp.getType());
        assertEquals(new BigDecimal("100.00"), resp.getAmount());
        verify(kafkaTemplate).send(eq("transactions"), any(TransactionResponse.class));
    }

    @Test
    void processTransaction_accountServiceError_throws() {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId("acc2");
        request.setType("WITHDRAW");
        request.setAmount(new BigDecimal("50"));

        when(restTemplate.postForEntity(eq("http://account-service/api/accounts/{id}/withdraw"),
                any(BalanceRequest.class), eq(Void.class), eq("acc2")))
            .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        assertThrows(ResponseStatusException.class, () -> service.processTransaction(request));
    }

    @Test
    void getTransactionById_existing_returns() {
        Transaction tx = new Transaction("acc1", "DEPOSIT", new BigDecimal("10"), LocalDateTime.now());
        tx.setId(2L);
        when(repository.findById(2L)).thenReturn(Optional.of(tx));

        TransactionResponse resp = service.getTransactionById(2L);
        assertEquals(2L, resp.getId());
    }

    @Test
    void getTransactionById_missing_throwsNotFound() {
        when(repository.findById(3L)).thenReturn(Optional.empty());
        assertThrows(TransactionNotFoundException.class, () -> service.getTransactionById(3L));
    }

    @Test
    void getAllTransactions_returnsList() {
        Transaction t1 = new Transaction("acc", "DEPOSIT", new BigDecimal("5"), LocalDateTime.now());
        t1.setId(1L);
        Transaction t2 = new Transaction("acc", "WITHDRAW", new BigDecimal("2"), LocalDateTime.now());
        t2.setId(2L);
        when(repository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<TransactionResponse> list = service.getAllTransactions();
        assertEquals(2, list.size());
    }
}
