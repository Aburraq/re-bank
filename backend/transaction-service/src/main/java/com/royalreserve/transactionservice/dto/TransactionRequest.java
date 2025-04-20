package com.royalreserve.transactionservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

public class TransactionRequest {
    @NotBlank(message = "Account ID is required")
    private String accountId;

    @NotBlank(message = "Transaction type is required")
    @Pattern(regexp = "DEPOSIT|WITHDRAW", message = "Type must be DEPOSIT or WITHDRAW")
    private String type;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be positive")
    private BigDecimal amount;

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
