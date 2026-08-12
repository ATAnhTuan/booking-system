package com.bookingSystem.auth;

import com.bookingSystem.exception.ErrorStatus;
import com.bookingSystem.users.User;
import com.bookingSystem.users.userEnum.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;


public class JwtTokenProvider {
    public static final String TOKEN_TYPE = "Bearer";
    private static final String TOKEN_PREFIX = TOKEN_TYPE + " ";
    private static final String ROLE = "role";
    private static final String USER_GUID = "userGuid";
    private static final String TYPE = "type";
    private static final String ACCESS = "access";
    private static final String REFRESH = "refresh";

    private Long durationOfExistenceAccess;
    private Long durationOfExistenceRefresh;
    private String key;
    private final SignatureAlgorithm SIGNATURE_ALG = SignatureAlgorithm.HS256;

    public String generateAccessToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .subject(user.getEmail())
                .claim(USER_GUID, user.getUserGuid().toString())
                .claim(ROLE, user.getRole().name())
                .claim(TYPE, ACCESS)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + durationOfExistenceAccess))
                .signWith(getSecretKey(), SIGNATURE_ALG)
                .compact();
    }

    public String generateRefreshToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .subject(user.getEmail())
                .claim(USER_GUID, user.getUserGuid().toString())
                .claim(TYPE, REFRESH)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + durationOfExistenceRefresh))
                .signWith(getSecretKey(), SIGNATURE_ALG)
                .compact();
    }


    public Claims validateAccessToken(String token) {
        Claims claims = parseClaims(stripBearerPrefix(token));
        if (!ACCESS.equals(claims.get(TYPE))) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    ErrorStatus.INVALID_TOKEN.name());
        }
        return claims;
    }

    public Claims validateRefreshToken(String token) {
        Claims claims = parseClaims(stripBearerPrefix(token));
        if (!REFRESH.equals(claims.get(TYPE))) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    ErrorStatus.INVALID_TOKEN.name());
        }
        return claims;
    }

    public AuthenticatedUser toAuthenticatedUser(Claims claims) {
        return new AuthenticatedUser(
                UUID.fromString(claims.get(USER_GUID, String.class)),
                claims.getSubject(),
                UserRole.valueOf(claims.get(ROLE, String.class))
        );
    }

    public Long getDurationOfExistenceAccess() {
        return durationOfExistenceAccess;
    }

    public String getTokenType() {
        return TOKEN_TYPE;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setDurationOfExistenceAccess(Long durationOfExistenceAccess) {
        this.durationOfExistenceAccess = durationOfExistenceAccess;
    }

    public void setDurationOfExistenceRefresh(Long durationOfExistenceRefresh) {
        this.durationOfExistenceRefresh = durationOfExistenceRefresh;
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String stripBearerPrefix(String token) {
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length()).trim();
        }
        return token;
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }
}
