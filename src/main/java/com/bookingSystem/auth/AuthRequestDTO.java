package com.bookingSystem.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AuthRequestDTO {
    @Email
    private String gmail;
    @NotBlank()
    private String password;

    public AuthRequestDTO() {
    }

    public AuthRequestDTO(String gmail, String password) {
        this.gmail = gmail;
        this.password = password;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }
}

