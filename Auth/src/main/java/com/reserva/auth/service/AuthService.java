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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class AuthService implements IAuthService{
    private final ITokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationManager;

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
//AuthenticationManager es una clase que provee spring boot para el manejo de autenticaciones
   // permite por ejemplo verificar la sesion con mail y contraseña.
    //Es necesario agregar mas configuraciones para indicar bien la forma en que se autentica
    // que usuario? de que forma? -> userDetailsService en appConfig
    @Override
    public TokenResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.mail(),request.password())
        );
        var user = userRepository.findByEmail(request.mail());
        if (user == null) throw new UsernameNotFoundException("Email not found");
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user); //En caso de que querramos que solo haya una sesion iniciada a la vez
        saveUserToken(user, jwtToken);
        return new TokenResponse(jwtToken,refreshToken);
    }

    private void revokeAllUserTokens(User user) {

    }

    public ResponseEntity<TokenResponse> refreshToken(String authHeader) {
    return null;}
}
