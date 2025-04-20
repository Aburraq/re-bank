package com.royalreserve.accountservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {
    private String id;
    private String ownerName;
    private String email;
    private BigDecimal balance;
    private LocalDateTime createdAt;

    public AccountResponse() {}

    public AccountResponse(String id, String ownerName, String email, BigDecimal balance, LocalDateTime createdAt) {
        this.id = id;
        this.ownerName = ownerName;
        this.email = email;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
