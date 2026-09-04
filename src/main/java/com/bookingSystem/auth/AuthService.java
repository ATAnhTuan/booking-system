package com.bookingSystem.auth;

import com.bookingSystem.exception.ErrorStatus;
import com.bookingSystem.exception.ResourceNotFoundException;
import com.bookingSystem.users.User;
import com.bookingSystem.users.dao.UserDAO;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;

public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public AuthService(JwtTokenProvider jwtTokenProvider, UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public AuthResponseDTO login(@Valid AuthRequestDTO request) {
        User user = userDAO.findByGmail(request.getGmail()).orElseThrow(() -> new ResourceNotFoundException(ErrorStatus.USER_NOT_FOUND.name()));
        if (!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorStatus.WRONG_PASSWORD.name());
        }
        AuthResponseDTO response = new AuthResponseDTO();

        response.setAccessToken(jwtTokenProvider.generateAccessToken(user));
        response.setRefreshToken(jwtTokenProvider.generateRefreshToken(user));
        response.setExpiresIn(jwtTokenProvider.getDurationOfExistenceAccess());
        response.setTokenType(jwtTokenProvider.getTokenType());

        return response;
    }
    @Transactional
    public AuthResponseDTO refreshToken( String refreshToken ) {
        Claims claims = jwtTokenProvider.validateRefreshToken(refreshToken);
        User user = userDAO.findByGmail(claims.getSubject()).orElseThrow(() -> new ResourceNotFoundException(ErrorStatus.USER_NOT_FOUND.name()));

        AuthResponseDTO response = new AuthResponseDTO();
        response.setAccessToken(jwtTokenProvider.generateAccessToken(user));
        response.setRefreshToken(jwtTokenProvider.generateRefreshToken(user));
        response.setExpiresIn(jwtTokenProvider.getDurationOfExistenceAccess());
        response.setTokenType(jwtTokenProvider.getTokenType());

        return response;
    }

}
