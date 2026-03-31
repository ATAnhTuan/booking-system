package com.bookingSystem.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class UsersEntity {
    private Long id;
    private UUID userGuid;
    private String username;
    private String password;
    private String email;
    private String role;
    private String memberRank;
    private boolean active;
    private String phone;
    private LocalDateTime createdAt;

    public UsersEntity(String username, String password, String email, String role, String memberRank, boolean active, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.memberRank = memberRank;
        this.active = active;
        this.phone = phone;
    }

    public UsersEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean getActive() {
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
