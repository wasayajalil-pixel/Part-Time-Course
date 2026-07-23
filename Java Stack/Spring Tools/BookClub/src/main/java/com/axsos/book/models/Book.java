package com.axsos.book.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotEmpty(message = "Book title is required")
	    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
	    private String title;

	    @NotEmpty(message = "Author is required")
	    @Size(min = 2, max = 100, message = "Author must be between 2 and 100 characters")
	    private String author;

	    @NotEmpty(message = "Description is required")
	    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
	    @Column(columnDefinition = "TEXT")
	    private String description;

	    /*
	     * Many books can belong to one user.
	     *
	     * user_id will be created inside the books table.
	     */
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id")
	    private User postedBy;

	    @Column(updatable = false)
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date createdAt;

	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private Date updatedAt;

	    public Book() {
	    }

	    @PrePersist
	    protected void onCreate() {
	        this.createdAt = new Date();
	    }

		@PreUpdate
	    protected void onUpdate() {
	        this.updatedAt = new Date();
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public User getPostedBy() {
			return postedBy;
		}

		public void setPostedBy(User postedBy) {
			this.postedBy = postedBy;
		}

		public Date getCreatedAt() {
			return createdAt;
		}


		public Date getUpdatedAt() {
			return updatedAt;
		}
	
}
