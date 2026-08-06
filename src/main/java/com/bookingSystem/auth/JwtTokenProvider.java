package com.bookingSystem.auth;

import com.bookingSystem.exception.ErrorStatus;
import com.bookingSystem.users.User;
import com.bookingSystem.users.UserService;
import com.bookingSystem.users.dto.UserResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Date;


public class JwtTokenProvider {
    final String TOKEN_TYPE = "Bearer ";
    final Long DURATION_OF_EXISTENCE_ACCESS = 1000 * 60 * 60L;  // 1 hous
    final Long DURATION_OF_EXISTENCE_REFRESH = 1000 * 60 * 60 * 24 * 7l; // 7 days
    private final String KEY = "9a8f7d6e5c4b3a29182736455463728190abcdef123456";
    private final Date ISSUED_AT = new Date();
    private final Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + DURATION_OF_EXISTENCE_ACCESS);
    private final Date EXPIRATION_TIME_REFRESH = new Date(System.currentTimeMillis() + DURATION_OF_EXISTENCE_REFRESH);
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(KEY.getBytes());
    private final SignatureAlgorithm SIGNATURE_ALG = SignatureAlgorithm.HS256;




    public String generateAccessToken(User user) {
        String jwtToken = Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole())
                .issuedAt(ISSUED_AT)
                .expiration(EXPIRATION_TIME)
                .signWith(SECRET_KEY, SIGNATURE_ALG)
                .compact();
        return jwtToken;
    }

    public String generateRefreshToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("type", "refresh")
                .issuedAt(ISSUED_AT)
                .expiration(EXPIRATION_TIME_REFRESH)
                .signWith(SECRET_KEY, SIGNATURE_ALG)
                .compact();
    }


    public Claims validateAccessToken(String token) {
        token = token.substring(TOKEN_TYPE.length()).trim();
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }

    public Claims validateRefreshToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        if (!"refresh".equals(claims.get("type"))) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    ErrorStatus.INVALID_TOKEN.name());
        }
        return claims;
    }

}
