package com.bookingSystem.dto.request;

public class UserRequestDTO {
    private String username;
    private String password;
    private String email;
    private String role;
    private String memberRank;
    //    private boolean active;
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

    public static UserRequestDTO withAll(String username, String password, String email,
                                         String role, String memberRank, String phone) {
        return new UserRequestDTO(username, password, email, role, memberRank, phone);
    }

    public static UserRequestDTO withoutRole(String username, String password,
                                          String email, String memberRank, String phone) {
        return new UserRequestDTO(username, password, email, null, memberRank, phone);
    }

    public static UserRequestDTO withoutMemberRank(String username, String password,
                                                String email, String role, String phone) {
        return new UserRequestDTO(username, password, email, role, null, phone);
    }

    public static UserRequestDTO withDefaults(String username, String password,
                                              String email, String phone) {
        return new UserRequestDTO(username, password, email, null, null, phone);
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
