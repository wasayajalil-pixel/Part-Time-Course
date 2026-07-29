package com.axsos.blogmanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

// @Entity: this class maps to a table in the database
@Entity
// The actual table name in MySQL is "users"
@Table(name = "users")
public class User {

    // Primary key, auto-incremented by MySQL
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "first is required!")
    @Pattern(regexp = "^[A-Za-z]+$", message = "first name must contain letters only!")
    @Size(min = 8, max = 30, message = "first name must be between 8 and 30 characters")
    private String firstName;

    @NotEmpty(message = "last name is required!")
    @Pattern(regexp = "^[A-Za-z]+$", message = "last name must contain letters only!")
    @Size(min = 8, max = 30, message = "last name must be between 8 and 30 characters")
    private String lastName;


    // Email - valid Email format, not blank
    // (checking that it does not already exist in the database
    //  happens in the service, because it needs a database query)
    @NotEmpty(message = "Email is required!")
    @Email(message = "Please enter a valid email!")
    private String email;

    // Password - at least 8 characters, not blank
    @NotEmpty(message = "Password is required!")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9]).+$",message = "Password sould contine an upper case numbers")
    private String password;

    // @Transient: this field is NOT stored in the database.
    // When saving a User with a JPA method, all transient fields are ignored,
    // and only the persistent fields are stored in the database.
    // We only use it temporarily to compare it against the password.
    @Transient
    @NotEmpty(message = "Confirm Password is required!")
    @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
    private String confirm;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Blog> blogs;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany(mappedBy = "borrower", fetch = FetchType.LAZY)
    private List<Blog> borrowedBlogs;


    // Empty constructor required by JPA
    public User() {}

    public User(Long id, String firstName, String lastName, String email, String password,
                String confirm, List<Blog> blogs, List<Blog> borrowedBlogs,List<Address> addresses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.blogs = blogs;
        this.borrowedBlogs = borrowedBlogs;
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt=new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt=new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public List<Blog> getProjects() {
        return blogs;
    }

    public void setProjects(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public List<Blog> getBorrowedProjects() {
        return borrowedBlogs;
    }

    public void setBorrowedProjects(List<Blog> borrowedBlogs) {
        this.borrowedBlogs = borrowedBlogs;
    }
}