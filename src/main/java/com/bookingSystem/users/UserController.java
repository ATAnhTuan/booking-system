package com.bookingSystem.users;


import com.bookingSystem.exception.ApiResponse;
import com.bookingSystem.users.dto.UserRequestDTO;
import com.bookingSystem.users.dto.UserResponseDTO;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
@Api(tags = "User Management")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{guid}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUser(@PathVariable UUID guid) {
        UserResponseDTO data = userService.getByGuid(guid);
        return ResponseEntity.ok(
                ApiResponse.success(data, "Success")
        );
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUsers() {
        List<UserResponseDTO> data = userService.getAllUsers();
        return ResponseEntity.ok(
                ApiResponse.success(data, "Success")
        );
    }

    @GetMapping("/header")
    public String Header(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO data = userService.createUser(userRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(data, "Success"));
    }

    @PutMapping("/{guid}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(@Valid
                                                                   @PathVariable UUID guid,
                                                                   @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO data = userService.updateUser(guid, userRequestDTO);
        return ResponseEntity.ok(ApiResponse.success(data, "Success"));
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID guid) {
        userService.deleteUser(guid);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{guid}")
    public ResponseEntity<?> deactivateUser(@PathVariable UUID guid, HttpServletRequest request) {
        String email = request.getAttribute("email").toString();
        String role = request.getAttribute("role").toString();
        System.out.println(role);
        System.out.println(email);
        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not allowed to delete users");
        }
        userService.deactivateUser(guid);
        return ResponseEntity.noContent().build();
    }
}
