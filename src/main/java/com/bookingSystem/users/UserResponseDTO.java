package com.bookingSystem.users;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserResponseDTO {
    private UUID userGuid;
    private String username;
    private String email;
    private UserRole role;
    private MemberRank memberRank;
    private boolean active;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public UserResponseDTO(String username, UUID userGuid, String email, UserRole role, MemberRank memberRank, boolean active, String phone, LocalDateTime createdAt) {
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public MemberRank getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(MemberRank memberRank) {
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
