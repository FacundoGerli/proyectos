package com.auth.service;

import com.auth.jwt.JwtService;
import com.auth.model.Token;
import com.auth.repository.ITokenRepository;
import com.auth.repository.IUserRepository;
import com.auth.usuario.Role;
import com.auth.usuario.User;
import com.auth.utils.LoginRequest;
import com.auth.utils.RegisterRequest;
import com.auth.utils.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.webauthn.api.AuthenticatorResponse;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final IUserRepository userRepository;
    private final ITokenRepository tokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;


    public TokenResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.getToken(user);
        var jwtRefresh = jwtService.getRefreshToken(user);
        saveUserToken(savedUser,jwtToken);
        return new TokenResponse(jwtToken,jwtRefresh);
    }

    private void saveUserToken(User savedUser, String jwtToken) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .tokenType(Token.TokenType.Bearer)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public TokenResponse login(LoginRequest request) {
        return null;
    }

    public TokenResponse refresh(String header) {
        return null;
    }
}
