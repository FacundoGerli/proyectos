package com.reserva.auth.controller;

public record LoginRequest(
        String username,
        String password
) {
}
