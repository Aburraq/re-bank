package com.royalreserve.assetservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AssetRequest {
    @NotBlank(message = "Owner ID is required")
    private String ownerId;

    @NotBlank(message = "Asset name is required")
    private String name;

    @NotNull(message = "Value is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Value must be positive")
    private BigDecimal value;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
