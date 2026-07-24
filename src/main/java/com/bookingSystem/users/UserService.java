package com.bookingSystem.users;

import com.bookingSystem.exception.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class UserService {
    private final UserDAO userDAO;
    private final UserMapper userMapper;

    public UserService(UserDAO userDAO, UserMapper userMapper) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO getByGuid(UUID guid) {
        return userMapper.toResponseDTO(findUserEntityByGuid(guid));
    }

    private User findUserEntityByGuid(UUID guid) {
        return userDAO.findByGuid(guid)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + guid));
    }

    @Transactional
    public List<UserResponseDTO> getAllUsers() {
        return userDAO.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User newUser = userMapper.toEntity(userRequestDTO);
        User savedUser = userDAO.save(newUser);
        return userMapper.toResponseDTO(savedUser);
    }

    @Transactional
    public UserResponseDTO updateUser(UUID guid, UserRequestDTO userRequestDTO) {
        User existingUser = findUserEntityByGuid(guid);
        userMapper.updateEntityFromDTO(userRequestDTO, existingUser);
        User updatedUser = userDAO.update(existingUser);
        return userMapper.toResponseDTO(updatedUser);
    }

    @Transactional
    public void deleteUser(UUID guid) {
        User user = findUserEntityByGuid(guid);
        userDAO.delete(user);
    }
}
