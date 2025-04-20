package com.royalreserve.transactionservice.service;

import com.royalreserve.transactionservice.dto.TransactionRequest;
import com.royalreserve.transactionservice.dto.TransactionResponse;
import java.util.List;

public interface TransactionService {
    TransactionResponse processTransaction(TransactionRequest request);
    TransactionResponse getTransactionById(Long id);
    List<TransactionResponse> getAllTransactions();
}
