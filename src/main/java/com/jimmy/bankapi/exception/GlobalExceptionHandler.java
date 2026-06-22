package com.jimmy.bankapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.security.access.AccessDeniedException;

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
    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(
            MethodArgumentNotValidException ex
    ) {

        String message =
                ex.getBindingResult()
                        .getFieldErrors()
                        .getFirst()
                        .getDefaultMessage();

        return new ErrorResponse(
                "VALIDATION_ERROR",
                message,
                LocalDateTime.now()
        );
    }
    @ExceptionHandler(
            AccessDeniedException.class
    )
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDenied(
            AccessDeniedException ex
    ) {

        return new ErrorResponse(
                "ACCESS_DENIED",
                "You do not have permission to access this resource",
                LocalDateTime.now()
        );
    }
}