package com.reserva.auth.service;

import com.reserva.auth.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
@Service
public class JwtService {
    @Value("${jwt.secret-key}")
    private String secret_key;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;
    @Value("${jwt.refresh-token.expiration}")
    private Long refreshExpiration;
    public JwtService(){
        System.out.println(secret_key);
    }

    public String generateToken(final User user){
        return buildToken(user, jwtExpiration);
    }
    public String generateRefreshToken(final User user){
        return buildToken(user, (refreshExpiration));
    }
    private String buildToken(final User user, final Long expiration){
        return Jwts.builder()
                .setId(user.getId().toString())
                .setClaims(Map.of("name", user.getUsername()))
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey())
                .compact();

    }
    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secret_key); //codificacion
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
