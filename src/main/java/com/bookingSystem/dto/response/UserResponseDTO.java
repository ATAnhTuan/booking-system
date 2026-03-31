package com.bookingSystem.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserResponseDTO {
    private UUID userGuid;
    private String username;
    private String email;
    private String role;
    private String memberRank;
    private boolean active;
    private String phone;
    private LocalDateTime createdAt;

    public UserResponseDTO(String username, UUID userGuid, String email, String role, String memberRank, boolean active, String phone, LocalDateTime createdAt) {
        this.username = username;
        this.userGuid = userGuid;
        this.email = email;
        this.role = role;
        this.memberRank = memberRank;
        this.active = active;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public UserResponseDTO() {
    }

    public UUID getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(UUID userGuid) {
        this.userGuid = userGuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(String memberRank) {
        this.memberRank = memberRank;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
