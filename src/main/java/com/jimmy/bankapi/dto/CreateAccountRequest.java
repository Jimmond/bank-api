package com.jimmy.bankapi.dto;

import java.math.BigDecimal;

public record CreateAccountRequest(
        String accountNumber,
        BigDecimal initialBalance,
        Long customerId
) {
}