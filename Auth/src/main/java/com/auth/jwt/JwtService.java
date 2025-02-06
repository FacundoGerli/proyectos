package com.auth.jwt;

import com.auth.usuario.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.refresh-token.expiration}")
    private Long refreshExpiration;




    public String getToken(final User user){
        return buildToken(user,expiration);
    }


    public String getRefreshToken(final User user) {
        return buildToken(user,refreshExpiration);
    }

    private String buildToken(final User user, final Long expiration) {
        return Jwts.builder()
                .id(user.getId().toString())
                .claims(Map.of("name",user.getUsername()))
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .compact();

    }

}
