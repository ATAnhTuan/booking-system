package com.bookingSystem.service;

import com.bookingSystem.dto.request.UserRequestDTO;
import com.bookingSystem.dto.response.UserResponseDTO;
import com.bookingSystem.entity.UsersEntity;
import com.bookingSystem.mapper.UserMapper;
import com.bookingSystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        if (userRequest == null) return null;
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        if(userRequest.getRole().isEmpty()  && userRequest.getMemberRank().isEmpty()){
             userRequestDTO.withDefaults(userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail(), userRequest.getPhone());
        }
        else if(userRequest.getRole().isEmpty()){
            userRequestDTO.withoutRole(userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail(),userRequest.getMemberRank(), userRequest.getPhone());
        }
        else if (userRequest.getMemberRank().isEmpty()){
            userRequestDTO.withoutMemberRank(userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail(),userRequest.getRole(), userRequest.getPhone());
        }
        else {
            userRequestDTO.withAll(userRequest.getUsername(), userRequest.getPassword(), userRequest.getEmail(),userRequest.getRole(),userRequest.getMemberRank(), userRequest.getPhone());
        }

        UsersEntity usersEntity = userMapper.toEntity(userRequestDTO);
        userRepository.save(usersEntity);
        return userMapper.toResponseDTO(usersEntity);
    }
}
