package com.jimmy.bankapi.dto;

import java.math.BigDecimal;

public record DepositRequest(
        BigDecimal amount
) {
}