package com.reserva.auth.controller;

public record TokenResponse(
        String jwtToken,
        String refreshToken
) {
}
