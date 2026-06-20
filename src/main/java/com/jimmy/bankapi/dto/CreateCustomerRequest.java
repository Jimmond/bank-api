package com.jimmy.bankapi.dto;

public record CreateCustomerRequest(
        String firstName,
        String lastName,
        String email
) {
}