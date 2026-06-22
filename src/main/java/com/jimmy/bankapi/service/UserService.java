package com.jimmy.bankapi.service;

import com.jimmy.bankapi.dto.request.RegisterRequest;
import com.jimmy.bankapi.entity.AppUser;
import com.jimmy.bankapi.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser register(
            RegisterRequest request
    ) {

        AppUser user = AppUser.builder()
                .username(request.username())
                .password(
                        passwordEncoder.encode(
                                request.password()
                        )
                )
                .role("USER")
                .build();

        return userRepository.save(user);
    }
}