package com.bookingSystem.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserRequestDTO {
    private String username;
    private String password;
    @Email(message = "Email ")
    private String email;
    private UserRole role;
    private MemberRank memberRank;
    //    private boolean active;
    @Pattern(
            regexp = "^(0|\\+84)[35789][0-9]{8}$",
            message = "Phone number invalid "
    )
    private String phone;

    private UserRequestDTO(String username, String password, String email, UserRole role, MemberRank memberRank, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.memberRank = memberRank;
        this.phone = phone;
    }

    public UserRequestDTO() {
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", memberRank='" + memberRank + '\'' +
                ", phone='" + phone + '\'' +
                '}';
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

//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
