package com.bookingSystem.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserRequestDTO {
    @Pattern(
            regexp = "^[a-zA-Z0-9_]{3,20}$",
            message = "Username chỉ được chứa chữ cái, chữ số, dấu gạch dưới và từ 3-20 ký tự"
    )
    private String username;
    @Pattern(
            regexp = "^^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,32}$",
            message = "Mật khẩu phải dài từ 6-32 ký tự, bao gồm cả chữ cái và chữ số"
    )
    private String password;
    @Email(message = "Email không đúng định dạng")
    private String email;
    private String role;
    private String memberRank;
    //    private boolean active;
    @Pattern(
            regexp = "^(0|\\+84)[3|5|7|8|9][0-9]{8}$",
            message = "Số điện thoại không đúng định dạng Việt Nam"
    )
    private String phone;

    private UserRequestDTO(String username, String password, String email, String role, String memberRank, String phone) {
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
