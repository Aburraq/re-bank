package com.royalreserve.accountservice.controller;

import com.royalreserve.accountservice.dto.AccountRequest;
import com.royalreserve.accountservice.dto.AccountResponse;
import com.royalreserve.accountservice.dto.BalanceRequest;
import com.royalreserve.accountservice.service.AccountService;
import com.royalreserve.accountservice.exception.AccountNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        AccountResponse created = accountService.createAccount(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(@PathVariable String id,
                                                   @Valid @RequestBody BalanceRequest request) {
        AccountResponse updated = accountService.deposit(id, request.getAmount());
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(@PathVariable String id,
                                                    @Valid @RequestBody BalanceRequest request) {
        AccountResponse updated = accountService.withdraw(id, request.getAmount());
        return ResponseEntity.ok(updated);
    }
}
