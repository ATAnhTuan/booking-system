package com.bookingSystem.users;


import com.bookingSystem.exception.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users")
@Api(tags = "User Management")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{guid}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUser(@PathVariable UUID guid) {
        UserResponseDTO data = userService.getByGuid(guid);
        return ResponseEntity.ok(
                ApiResponse.success(data, "User found Successfully")
        );
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>> getUsers() {
        List<UserResponseDTO> data = userService.getAllUsers();
        return ResponseEntity.ok(
                ApiResponse.success(data, "Users found Successfully")
        );
    }
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO data = userService.createUser(userRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(data, "Create Users Success"));
    }

    @PutMapping("/{guid}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(
            @PathVariable UUID guid,
            @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO data = userService.updateUser(guid, userRequestDTO);
        return ResponseEntity.ok(ApiResponse.success(data, "User Updated Success"));
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID guid) {
        userService.deleteUser(guid);
        return ResponseEntity.noContent().build();
    }
}
