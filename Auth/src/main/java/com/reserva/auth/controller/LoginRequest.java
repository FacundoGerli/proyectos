package com.reserva.auth.controller;

public record LoginRequest(
        String mail,
        String password
) {
}
