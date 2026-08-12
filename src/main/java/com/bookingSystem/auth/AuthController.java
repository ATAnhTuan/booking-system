package com.bookingSystem.auth;

import com.bookingSystem.exception.ApiResponse;
import com.bookingSystem.users.UserService;
import com.bookingSystem.users.dto.UserRequestDTO;
import com.bookingSystem.users.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/refresh")
    public ResponseEntity refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(ApiResponse.success(authService.refreshToken(request.getRefreshToken()),"Success"));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.success(authService.login(request),"Success"));
    }
}
