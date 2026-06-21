package com.jimmy.bankapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            InsufficientFundsException.class
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInsufficientFunds(
            InsufficientFundsException ex
    ) {

        return new ErrorResponse(
                "INSUFFICIENT_FUNDS",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(
            AccountNotFoundException.class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleAccountNotFound(
            AccountNotFoundException ex
    ) {

        return new ErrorResponse(
                "ACCOUNT_NOT_FOUND",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(
            Exception.class
    )
    @ResponseStatus(
            HttpStatus.INTERNAL_SERVER_ERROR
    )
    public ErrorResponse handleGeneric(
            Exception ex
    ) {

        return new ErrorResponse(
                "INTERNAL_ERROR",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }
}