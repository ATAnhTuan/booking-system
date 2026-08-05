package com.bookingSystem.auth;

import com.bookingSystem.users.User;
import com.bookingSystem.users.dao.UserDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;


public class AuthenticationService {
    private final String KEY = "9a8f7d6e5c4b3a29182736455463728190abcdef123456";
    private final Date ISSUED_AT = new Date();
    private final Date EXPIRATION_TIME = new Date(System.currentTimeMillis() + (1000 * 60 * 60));
    private final Date EXPIRATION_TIME_REFRESH = new Date(System.currentTimeMillis() + (7 * 24 * 1000 * 60 * 60));
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(KEY.getBytes());
    private  UserDAO  userDAO;



    public String generateAccessToken(User user) {
        String jwtToken = Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole())
                .issuedAt(ISSUED_AT)
                .expiration(EXPIRATION_TIME)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
        return jwtToken;
    }

    public String generateRefreshToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("type", "refresh")
                .issuedAt(ISSUED_AT)
                .expiration(EXPIRATION_TIME_REFRESH)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256  )
                .compact();
    }

    public String refreshAccessToken(String refreshToken) {
            Claims claims = validateAndExtractClaims(refreshToken);
            String gmail = claims.getSubject();
            User user = userDAO.getByGmail(gmail);
            return generateAccessToken(user);
    }

    public Claims validateAndExtractClaims(String token) {
        if (token.contains("Bearer ")) {
            token = token.substring(7);
        }
        Claims claims = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        if (claims.getExpiration().before(new Date())) {
            throw new RuntimeException("Token expired");
        }
        return claims;
    }

}
