package com.axsos.blogmanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blog_id;

    @NotEmpty(message = "please fill the title")
    @Size(min = 2,max = 120,message = "please fill the blank")
    private String title;

    @NotEmpty(message = "please fill the category")
    @Size(min=2,max = 30,message = "please make sure the category  between 2 and 30 charcters")
    private String category;

    @NotEmpty(message = "plaese fill the content")
    @Size(min = 20,max = 120,message = "content must be 20 charcters or more")
    private String content;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "MMM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "MMM-dd")
    private Date updatedAt;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id")
    private User borrower;

    public Blog(){}
    public Blog(Long blog_id, String title, String content, User user, User borrower,String category,List<Comment> comments) {
        this.blog_id = blog_id;
        this.comments = comments;
        this.borrower = borrower;
        this.title = title;
        this.user = user;
        this.content = content;
        this.category= category;
    }


    @PrePersist
    protected void onCreate(){
        this.createdAt=new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt=new Date();
    }


    public Long getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(Long blog_id) {
        this.blog_id = blog_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public User getUser() {
        return user;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
