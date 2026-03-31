package com.bookingSystem.mapper;

import com.bookingSystem.dto.request.UserRequestDTO;
import com.bookingSystem.dto.response.UserResponseDTO;
import com.bookingSystem.entity.UsersEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO toResponseDTO(UsersEntity userEntity) {
        if (userEntity == null) return null;
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserGuid(userEntity.getUserGuid());
        userResponseDTO.setUsername(userEntity.getUsername());
        userResponseDTO.setEmail(userEntity.getEmail());
        userResponseDTO.setPhone(userEntity.getPhone());
        userResponseDTO.setRole(userEntity.getRole());
        userResponseDTO.setMemberRank(userEntity.getMemberRank());
        userResponseDTO.setActive(userEntity.getActive());
        userResponseDTO.setCreatedAt(userEntity.getCreatedAt());

        return userResponseDTO;
    }


    public UsersEntity toEntity(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) return null;

        UsersEntity userEntity = new UsersEntity();

        if (userRequestDTO.getUsername() == null || userRequestDTO.getUsername().trim().isEmpty())
            throw new IllegalArgumentException("Username is required");

        if (userRequestDTO.getEmail() == null || userRequestDTO.getEmail().trim().isEmpty())
            throw new IllegalArgumentException("Email is required");

        if (userRequestDTO.getPassword() == null || userRequestDTO.getPassword().trim().isEmpty())
            throw new IllegalArgumentException("Password is required");
        userEntity.setUserGuid(UUID.randomUUID());
        userEntity.setUsername(userRequestDTO.getUsername());
        userEntity.setEmail(userRequestDTO.getEmail());
        userEntity.setPhone(userRequestDTO.getPhone());
        userEntity.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        if (userRequestDTO.getRole() != null)
            userEntity.setRole(userRequestDTO.getRole());
        if (userRequestDTO.getMemberRank() != null)
            userEntity.setMemberRank(userRequestDTO.getMemberRank());
        return userEntity;
    }

}
