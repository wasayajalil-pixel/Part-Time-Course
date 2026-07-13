package com.axsos.gameM.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    // First name cannot be empty and must be at least 4 chars
	    @NotBlank(message = "First name is required")
	    @Size(min = 4, message = "First name must be at least 4 characters")
	    private String firstName;

	    // Last name cannot be empty and must be at least 4 chars
	    @NotBlank(message = "Last name is required")
	    @Size(min = 4, message = "Last name must be at least 4 characters")
	    private String lastName;

	    // Email must be valid
	    @NotBlank(message = "Email is required")
	    @Email(message = "Please enter a valid email")
	    private String email;

	    // Password must be at least 8 chars
	    @NotBlank(message = "Password is required")
	    @Size(min = 8, message = "Password must be at least 8 characters")
	    private String password;

	    // This field will NOT be saved in DB
	    @Transient
	    @NotBlank(message = "Confirm password is required")
	    private String confirmPassword;

	    // User must enter date of birth
	    @NotNull(message = "Date of birth is required")
	    @Past(message = "Date of birth must be in the past")
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date dateOfBirth;

	    // One user can create many games
	    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
	    private List<Game> createdGames;

	    // Many users can favorite many games
	    @ManyToMany
	    @JoinTable(
	        name = "favorites",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "game_id")
	    )
	    private List<Game> favoriteGames;

	    // One user can rate many games
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	    private List<Rating> ratings;

	    @Column(updatable = false)
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date createdAt;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date updatedAt;

	    public User() {}

	    @PrePersist
	    protected void onCreate() {
	        this.createdAt = new Date();
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        this.updatedAt = new Date();
	    }

	    // Getters and Setters
	    public List<Game> getCreatedGames() {
			return createdGames;
		}

		public void setCreatedGames(List<Game> createdGames) {
			this.createdGames = createdGames;
		}

		public List<Game> getFavoriteGames() {
			return favoriteGames;
		}

		public void setFavoriteGames(List<Game> favoriteGames) {
			this.favoriteGames = favoriteGames;
		}

		public List<Rating> getRatings() {
			return ratings;
		}

		public void setRatings(List<Rating> ratings) {
			this.ratings = ratings;
		}

	    public Long getId() {
	        return id;
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

	    public String getFullName() {
	        return this.firstName + " " + this.lastName;
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

	    public Date getDateOfBirth() {
	        return dateOfBirth;
	    }

	    public void setDateOfBirth(Date dateOfBirth) {
	        this.dateOfBirth = dateOfBirth;
	    }

	}


