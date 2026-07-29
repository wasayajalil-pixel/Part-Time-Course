package com.axsos.blogmanager.services;

import com.axsos.blogmanager.models.Blog;
import com.axsos.blogmanager.models.Comment;
import com.axsos.blogmanager.models.User;
import com.axsos.blogmanager.repositires.BlogRepo;
import com.axsos.blogmanager.repositires.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BlogRepo blogRepo;

    public CommentService(CommentRepo commentRepo, BlogRepo blogRepo) {
        this.commentRepo = commentRepo;
        this.blogRepo = blogRepo;
    }

    //show all
    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    public void createCommentCall(Comment comment) {
        commentRepo.save(comment);
    }

    public List<Comment> getMyComments(Blog currentBlog) {
        return commentRepo.findByBlog(currentBlog);
    }
}
