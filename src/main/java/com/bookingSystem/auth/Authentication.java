package com.bookingSystem.auth;

import com.bookingSystem.exception.ApiResponse;
import com.bookingSystem.users.UserService;
import com.bookingSystem.users.dto.UserRequestDTO;
import com.bookingSystem.users.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class Authentication {
    private final UserService userService;

    public Authentication(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserRequestDTO request) {
        String data = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ApiResponse.success(data, "Success"));
    }
}
