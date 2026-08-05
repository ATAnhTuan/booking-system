package com.bookingSystem.auth;

import com.bookingSystem.exception.ApiResponse;
import com.bookingSystem.users.UserService;
import com.bookingSystem.users.dto.UserRequestDTO;
import com.bookingSystem.users.dto.UserResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@RestController
public class Authentication {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public Authentication(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }


        @PostMapping("/refresh")
        public ResponseEntity<?> refreshToken(
                @RequestBody String refreshToken) {
            Map<String, String> response = new HashMap<>();
            response.put("accessToken", authenticationService.refreshAccessToken(refreshToken));
             return ResponseEntity.ok(ApiResponse.success(response,"refreshToken"));
        }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequestDTO request) {
        HashMap response = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ApiResponse.success(response,"Success"));
    }
}
