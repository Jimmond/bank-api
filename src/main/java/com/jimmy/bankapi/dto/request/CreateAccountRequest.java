package com.jimmy.bankapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateAccountRequest(

        @NotBlank(message = "Account number is required")
        String accountNumber,

        @NotNull(message = "Initial balance is required")
        @Positive(message = "Balance must be positive")
        BigDecimal initialBalance,

        @NotNull(message = "Customer id is required")
        Long customerId

) {
}