package com.bookingSystem.users.dao;

import com.bookingSystem.users.User;
import com.bookingSystem.users.dto.UserResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDAO {
    User save(User user);
    User update(User user);
    void delete(User user);
    void deactivate(User user);
    Optional<User> findByGuid(UUID guid);
    Optional<User> login(String gmail);
    List<User> findAll();
    User getByGmail(String gmail);
}
