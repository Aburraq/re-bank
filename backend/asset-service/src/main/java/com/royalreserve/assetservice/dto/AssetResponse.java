package com.royalreserve.assetservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AssetResponse {
    private Long id;
    private String ownerId;
    private String name;
    private BigDecimal value;
    private LocalDateTime createdAt;

    public AssetResponse() {}

    public AssetResponse(Long id, String ownerId, String name, BigDecimal value, LocalDateTime createdAt) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.value = value;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
