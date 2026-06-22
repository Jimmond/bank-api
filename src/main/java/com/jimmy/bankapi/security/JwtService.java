package com.jimmy.bankapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key =
            Keys.secretKeyFor(
                    SignatureAlgorithm.HS256
            );

    public String generateToken(
            String username
    ) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 24
                        )
                )
                .signWith(key)
                .compact();
    }
    public String extractUsername(
            String token
    ) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean isTokenValid(
            String token,
            String username
    ) {

        return extractUsername(token)
                .equals(username);
    }
}