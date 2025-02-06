package com.auth.utils;

public record LoginRequest(
        String email,
        String password
) {
}
