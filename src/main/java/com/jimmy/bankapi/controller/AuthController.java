package com.jimmy.bankapi.controller;

import com.jimmy.bankapi.dto.request.LoginRequest;
import com.jimmy.bankapi.dto.request.RegisterRequest;
import com.jimmy.bankapi.dto.response.LoginResponse;
import com.jimmy.bankapi.entity.AppUser;
import com.jimmy.bankapi.security.JwtService;
import com.jimmy.bankapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(
            UserService userService,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public AppUser register(

            @Valid
            @RequestBody RegisterRequest request

    ) {

        return userService.register(request);
    }
    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        String token =
                jwtService.generateToken(
                        request.username()
                );

        return new LoginResponse(token);
    }
}