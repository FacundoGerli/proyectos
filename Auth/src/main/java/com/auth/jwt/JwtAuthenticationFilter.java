package com.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
//El extends define a nuestra clase como filtro, y el oncePerRequest significa que se va a ejecutar
//una sola vez por request.
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //Este metodo va a realizar los filtros para el token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String token = getTokenFromRequest(request);

        if(token == null) {
            filterChain.doFilter(request,response);
            return;
        }
        filterChain.doFilter(request,response);

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(authHeader) && authHeader.contains("BEARER")){
            return authHeader.substring(7);
        }
        return null;
    }
}
