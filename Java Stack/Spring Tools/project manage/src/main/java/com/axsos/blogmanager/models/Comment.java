package com.axsos.blogmanager.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @NotEmpty(message = "please fill the comment")
    @Size(min = 10,max = 50, message = "make sure it is range between 10 and 50")
    private String commentContent;


    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;


    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "MMM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "MMM-dd")
    private Date updatedAt;

    public Comment(){}

    public Comment(Long comment_id, String commentContent, Date createdAt, Date updatedAt,User user,Blog blog) {
        this.comment_id = comment_id;
        this.user =user;
        this.blog=blog;
        this.commentContent = commentContent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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

    @PrePersist
    protected void onCreate(){
        this.createdAt=new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt=new Date();
    }

}
