package com.example.learn_security.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class RefreshToken {
    private String token;
    private Instant expiryDate;
}