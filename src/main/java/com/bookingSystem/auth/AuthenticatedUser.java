package com.bookingSystem.auth;

import com.bookingSystem.users.userEnum.UserRole;

import java.util.UUID;

public class AuthenticatedUser {
    private final UUID userGuid;
    private final String email;
    private final UserRole role;

    public AuthenticatedUser(UUID userGuid, String email, UserRole role) {
        this.userGuid = userGuid;
        this.email = email;
        this.role = role;
    }

    public UUID getUserGuid() {
        return userGuid;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }
}
