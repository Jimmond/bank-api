package com.jimmy.bankapi.dto.response;

public record CustomerResponse(

        Long id,

        String firstName,

        String lastName,

        String email

) {
}