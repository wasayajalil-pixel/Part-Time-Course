package com.axsos.authentication.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginUser {

    // Email entered on the login form
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    private String email;

    // Password entered on the login form
    @NotBlank(message = "Password is required")
    private String password;

    // Empty constructor
    public LoginUser() {
    }

    // ==========================
    // Getters & Setters
    // ==========================

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}