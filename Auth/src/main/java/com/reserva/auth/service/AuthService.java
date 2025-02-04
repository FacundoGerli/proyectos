package com.reserva.auth.service;
import com.reserva.auth.controller.LoginRequest;
import com.reserva.auth.controller.RegisterRequest;
import com.reserva.auth.controller.TokenResponse;
import com.reserva.auth.model.Token;
import com.reserva.auth.model.TokenType;
import com.reserva.auth.model.User;
import com.reserva.auth.repository.ITokenRepository;
import com.reserva.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService{
    private final ITokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public TokenResponse register(RegisterRequest request) {
        var user = User.builder().email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .username(request.username())
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return new TokenResponse(jwtToken, refreshToken);
    }

    private void saveUserToken(User savedUser, String jwtToken) {
        var token = Token.builder()
                .user(savedUser)
                .token(jwtToken)
                .revoked(false)
                .expired(false)
                .tokenType(TokenType.BEARER)
                .build();
        tokenRepository.save(token);
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        return null;
    }

    public ResponseEntity<TokenResponse> refreshToken(String authHeader) {
    return null;}
}
