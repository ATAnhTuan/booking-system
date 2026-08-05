package com.bookingSystem.users;

import com.bookingSystem.auth.AuthenticationService;
import com.bookingSystem.exception.ErrorStatus;
import com.bookingSystem.exception.ResourceNotFoundException;
import com.bookingSystem.users.dao.UserDAO;
import com.bookingSystem.users.dto.UserMapper;
import com.bookingSystem.users.dto.UserRequestDTO;
import com.bookingSystem.users.dto.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDAO userDAO;
    private final UserMapper userMapper;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserDAO userDAO, UserMapper userMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDAO = userDAO;
        this.userMapper = userMapper;
    }


    @Transactional
    public UserResponseDTO getByGuid(UUID guid) {
        return userMapper.toResponseDTO(findUserEntityByGuid(guid));
    }

    private User findUserEntityByGuid(UUID guid) {
        return userDAO.findByGuid(guid)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorStatus.USER_NOT_FOUND.name()));
    }

    @Transactional
    public HashMap<String,String> login(String gmail, String password) {
        User user = userDAO.login(gmail)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorStatus.USER_NOT_FOUND.name()));

        UserResponseDTO userResponseDTO = userMapper.toResponseDTO(user);

        if (!bCryptPasswordEncoder.matches(password, userResponseDTO.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        String accessToken = new AuthenticationService().generateAccessToken(user);
        String refreshToken = new AuthenticationService().generateRefreshToken(user);

        HashMap<String,String> response = new HashMap<>();
        response.put("accessToken", accessToken);
        response.put("refreshToken", refreshToken);

        return response;
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

    @Transactional
    public void deactivateUser(UUID guid) {
        User user = findUserEntityByGuid(guid);
        userDAO.deactivate(user);
    }
}
