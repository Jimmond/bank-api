package com.jimmy.bankapi.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import com.jimmy.bankapi.entity.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}