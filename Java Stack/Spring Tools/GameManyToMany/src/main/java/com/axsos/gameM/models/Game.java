package com.axsos.gameM.models;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Game name must be at least 2 characters
    @NotBlank(message = "Game name is required")
    @Size(min = 2, message = "Game name must be at least 2 characters")
    private String gameName;

    @NotBlank(message = "Genre is required")
    private String genre;

    // Release date cannot be empty
    @NotNull(message = "Release date is required")
    @PastOrPresent(message = "Release date cannot be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @NotBlank(message = "Description is required")
    private String description;

    // Many games can be created by one user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    // Many games can be favorite by many users
    @ManyToMany(mappedBy = "favoriteGames",fetch = FetchType.LAZY)
    private List<User> likedByUsers;

    // One game can have many ratings
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    public Game() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setLikedByUsers(List<User> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getLikedByUsers() {
        return likedByUsers;
    }

    public List<Rating> getRatings() {
        return ratings;
    }
}