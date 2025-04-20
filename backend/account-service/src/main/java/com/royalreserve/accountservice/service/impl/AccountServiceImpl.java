package com.royalreserve.accountservice.service.impl;

import com.royalreserve.accountservice.dto.AccountRequest;
import com.royalreserve.accountservice.dto.AccountResponse;
import com.royalreserve.accountservice.exception.AccountNotFoundException;
import com.royalreserve.accountservice.exception.InsufficientFundsException;
import com.royalreserve.accountservice.model.Account;
import com.royalreserve.accountservice.repository.AccountRepository;
import com.royalreserve.accountservice.service.AccountService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    @CachePut(value = "accounts", key = "#result.id")
    public AccountResponse createAccount(AccountRequest request) {
        Account acc = new Account(
                request.getOwnerName(),
                request.getEmail(),
                request.getInitialBalance(),
                LocalDateTime.now()
        );
        Account saved = repository.save(acc);
        return toResponse(saved);
    }

    @Override
    @Cacheable(value = "accounts", key = "#id")
    public AccountResponse getAccountById(String id) {
        Account acc = repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        return toResponse(acc);
    }

    @Override
    public List<AccountResponse> getAllAccounts() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "accounts", key = "#id")
    public void deleteAccount(String id) {
        if (!repository.existsById(id)) {
            throw new AccountNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    @CachePut(value = "accounts", key = "#result.id")
    public AccountResponse deposit(String id, BigDecimal amount) {
        Account acc = repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        acc.setBalance(acc.getBalance().add(amount));
        Account saved = repository.save(acc);
        return toResponse(saved);
    }

    @Override
    @CachePut(value = "accounts", key = "#result.id")
    public AccountResponse withdraw(String id, BigDecimal amount) {
        Account acc = repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        if (acc.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException(id);
        }
        acc.setBalance(acc.getBalance().subtract(amount));
        Account saved = repository.save(acc);
        return toResponse(saved);
    }

    private AccountResponse toResponse(Account acc) {
        return new AccountResponse(
                acc.getId(),
                acc.getOwnerName(),
                acc.getEmail(),
                acc.getBalance(),
                acc.getCreatedAt()
        );
    }
}
