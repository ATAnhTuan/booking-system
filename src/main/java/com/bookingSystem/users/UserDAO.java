package com.bookingSystem.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDAO {
    UserEntity save(UserEntity user);
    UserEntity update(UserEntity user);
    void delete(UserEntity user);
    Optional<UserEntity> findByGuid(UUID guid);
    List<UserEntity> findAll();
}
