package com.royalreserve.transactionservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponse {
    private Long id;
    private String accountId;
    private String type;
    private BigDecimal amount;
    private LocalDateTime createdAt;

    public TransactionResponse() {}

    public TransactionResponse(Long id, String accountId, String type, BigDecimal amount, LocalDateTime createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
