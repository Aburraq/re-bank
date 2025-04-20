package com.royalreserve.transactionservice.controller;

import com.royalreserve.transactionservice.dto.TransactionRequest;
import com.royalreserve.transactionservice.dto.TransactionResponse;
import com.royalreserve.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> processTransaction(@Valid @RequestBody TransactionRequest request) {
        TransactionResponse resp = service.processTransaction(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public List<TransactionResponse> getAll() {
        return service.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionResponse getById(@PathVariable Long id) {
        return service.getTransactionById(id);
    }
}
