package com.bookingSystem.users;

import com.bookingSystem.exception.ErrorStatus;
import com.bookingSystem.exception.ResourceNotFoundException;
import com.bookingSystem.users.dao.UserDAO;
import com.bookingSystem.users.dto.UserMapper;
import com.bookingSystem.users.dto.UserRequestDTO;
import com.bookingSystem.users.dto.UserResponseDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import javax.transaction.Transactional;
import java.util.Date;
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
    public String login(String gmail, String password) {
        User user = userDAO.login(gmail)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorStatus.USER_NOT_FOUND.name()));
        UserResponseDTO userResponseDTO = userMapper.toResponseDTO(user);
        if (!bCryptPasswordEncoder.matches(password, userResponseDTO.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        String key = "9a8f7d6e5c4b3a29182736455463728190abcdef123456";
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());
        String jwtToken = Jwts.builder()
                .subject(user.getUsername())
                .subject(user.getEmail())
                .subject(user.getMemberRank().name())
                .claim("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()
                        + 30 * 60 * 1000L))
                .signWith(secretKey)
                .compact();
        return jwtToken;
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
