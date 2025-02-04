package com.reserva.auth.controller;

public record RegisterRequest(
        String username,
        String password,
        String email
) {
}
