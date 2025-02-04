package com.reserva.auth.service;

import com.reserva.auth.controller.LoginRequest;
import com.reserva.auth.controller.RegisterRequest;
import com.reserva.auth.controller.TokenResponse;
import com.reserva.auth.model.User;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    public TokenResponse register(RegisterRequest request);
    public TokenResponse login(LoginRequest request);
    public ResponseEntity<TokenResponse> refreshToken(String authHeader);
}
