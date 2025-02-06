package com.auth.utils;

public record RegisterRequest(
        String username,
        String email,
        String password
) {
}
