package com.bookingSystem.users.dto;

import com.bookingSystem.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO toResponseDTO(User userEntity) {
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


    public User toEntity(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) return null;

        User userEntity = new User();

        userEntity.setUserGuid(UUID.randomUUID());
        userEntity.setUsername(userRequestDTO.getUsername());
        userEntity.setEmail(userRequestDTO.getEmail());
        userEntity.setPhone(userRequestDTO.getPhone());
        userEntity.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        if (userRequestDTO.getRole() != null) userEntity.setRole(userRequestDTO.getRole());
        if (userRequestDTO.getMemberRank() != null) userEntity.setMemberRank(userRequestDTO.getMemberRank());

        return userEntity;
    }

    public void updateEntityFromDTO(UserRequestDTO dto, User userEntity) {
        if (dto == null || userEntity == null) return;

        if (dto.getUsername() != null && !dto.getUsername().trim().isEmpty()) {
            userEntity.setUsername(dto.getUsername());
        }

        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
            userEntity.setEmail(dto.getEmail());
        }

        if (dto.getPhone() != null) {
            userEntity.setPhone(dto.getPhone());
        }

        if (dto.getRole() != null) {
            userEntity.setRole(dto.getRole());
        }

        if (dto.getMemberRank() != null) {
            userEntity.setMemberRank(dto.getMemberRank());
        }

        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
    }

}
