package com.jimmy.bankapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jimmy.bankapi.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint
        implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {

        response.setStatus(
                HttpServletResponse.SC_UNAUTHORIZED
        );

        response.setContentType(
                "application/json"
        );

        ErrorResponse error =
                new ErrorResponse(
                        "UNAUTHORIZED",
                        "Authentication is required",
                        LocalDateTime.now()
                );

        new ObjectMapper()
                .writeValue(
                        response.getOutputStream(),
                        error
                );
    }
}