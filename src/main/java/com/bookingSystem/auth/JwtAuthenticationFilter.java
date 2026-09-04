package com.bookingSystem.auth;

import io.jsonwebtoken.Claims;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            Claims tokenValid = jwtTokenProvider.validateAccessToken(token);
            String email = tokenValid.getSubject();
            String role  = tokenValid.get("role").toString();

            filterChain.doFilter(request, response);
        }catch (Exception ex){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
