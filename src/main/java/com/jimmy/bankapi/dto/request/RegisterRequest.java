package com.jimmy.bankapi.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(

        @NotBlank
        String username,

        @NotBlank
        String password

) {
}