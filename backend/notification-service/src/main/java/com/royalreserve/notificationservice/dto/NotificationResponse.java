package com.royalreserve.notificationservice.dto;

import java.time.LocalDateTime;

public class NotificationResponse {
    private Long id;
    private String accountId;
    private String message;
    private LocalDateTime createdAt;

    public NotificationResponse() {}

    public NotificationResponse(Long id, String accountId, String message, LocalDateTime createdAt) {
        this.id = id;
        this.accountId = accountId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
