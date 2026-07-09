package com.axsos.authentication.models;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Username validation
    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Username must be at least 3 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Username must contain letters only")
    private String username;

    // Email validation
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    private String email;

    // Password validation
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
        message = "Password must contain one uppercase letter and one number"
    )
    private String password;

    // Confirm password is NOT saved in database
    @Transient
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    @Column(updatable = false)
    private Date createdAt;

    private Date updatedAt;

    public User() {}

    // Runs before creating user
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    // Runs before updating user
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    // Getters and setters

    public Long getId() {
        return id;
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
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}