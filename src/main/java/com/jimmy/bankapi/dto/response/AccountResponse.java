package com.jimmy.bankapi.dto.response;

import java.math.BigDecimal;

public record AccountResponse(

        Long id,

        String accountNumber,

        BigDecimal balance,

        String customerName

) {
}