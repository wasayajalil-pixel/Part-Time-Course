package com.axsos.game.models;


import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Cannot be empty
	// Minimum 4 letters
	@NotBlank(message = "First name is required")
	@Size(min = 4, max = 255, message = "First name must be at least 4 characters")
	private String firstName;
	
	@NotBlank(message = "Last name is required")
	@Size(min = 4, max = 255, message = "Last name must be at least 4 characters")
	private String lastName;
	
	// Email Validation
	@Email(message = "Email is invalid")
	@NotBlank(message = "Email is required")
	private String email;
	
	// Password validation
	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;

	// This field exists only in the form.
	// It will NOT be saved inside the database.
	@Transient
	private String confirm;
	
	// User must be 18+
    @Column(name = "birthday")
    private LocalDate birthday;

	// Store uploaded image path
	private String avatar;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private List<Game> games;

	
//	Empty Constructor
	public User() {
	}
	
//	Getter & Setter
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
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    // Check if the user is at least 18 years old.
    public boolean isAdult() {
        if (birthday == null) {
            return false;
        }

        return Period.between(birthday, LocalDate.now()).getYears() >= 18;
    }


}
