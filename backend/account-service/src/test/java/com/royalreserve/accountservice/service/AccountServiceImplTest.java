package com.royalreserve.accountservice.service;

import com.royalreserve.accountservice.dto.AccountRequest;
import com.royalreserve.accountservice.dto.AccountResponse;
import com.royalreserve.accountservice.exception.AccountNotFoundException;
import com.royalreserve.accountservice.model.Account;
import com.royalreserve.accountservice.repository.AccountRepository;
import com.royalreserve.accountservice.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {
    @Mock
    private AccountRepository repository;

    private AccountServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new AccountServiceImpl(repository);
    }

    @Test
    void createAccount_shouldReturnResponse() {
        AccountRequest req = new AccountRequest();
        req.setOwnerName("John Doe");
        req.setEmail("john@example.com");
        req.setInitialBalance(new BigDecimal("100.00"));

        Account saved = new Account("John Doe","john@example.com",new BigDecimal("100.00"),LocalDateTime.now());
        saved.setId("abc123");
        when(repository.save(any(Account.class))).thenReturn(saved);

        AccountResponse resp = service.createAccount(req);
        assertEquals("abc123", resp.getId());
        assertEquals("John Doe", resp.getOwnerName());
        assertEquals(new BigDecimal("100.00"), resp.getBalance());
        verify(repository).save(any(Account.class));
    }

    @Test
    void getAccountById_existing_returnsResponse() {
        Account acc = new Account("Jane","jane@example.com",new BigDecimal("50"),LocalDateTime.now());
        acc.setId("id1");
        when(repository.findById("id1")).thenReturn(Optional.of(acc));

        AccountResponse resp = service.getAccountById("id1");
        assertEquals("id1", resp.getId());
        assertEquals("Jane", resp.getOwnerName());
    }

    @Test
    void getAccountById_missing_throwsNotFound() {
        when(repository.findById("id2")).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> service.getAccountById("id2"));
    }

    @Test
    void getAllAccounts_returnsList() {
        Account a1 = new Account("A","a@example.com",BigDecimal.ZERO,LocalDateTime.now());
        a1.setId("1");
        Account a2 = new Account("B","b@example.com",new BigDecimal("10"),LocalDateTime.now());
        a2.setId("2");
        when(repository.findAll()).thenReturn(Arrays.asList(a1,a2));

        List<AccountResponse> list = service.getAllAccounts();
        assertEquals(2, list.size());
    }

    @Test
    void deleteAccount_existing_deletes() {
        when(repository.existsById("1")).thenReturn(true);
        service.deleteAccount("1");
        verify(repository).deleteById("1");
    }

    @Test
    void deleteAccount_missing_throwsNotFound() {
        when(repository.existsById("2")).thenReturn(false);
        assertThrows(AccountNotFoundException.class, () -> service.deleteAccount("2"));
    }
}
