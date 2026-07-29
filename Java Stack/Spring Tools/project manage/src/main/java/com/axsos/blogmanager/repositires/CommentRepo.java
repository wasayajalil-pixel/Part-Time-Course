package com.axsos.blogmanager.repositires;

import com.axsos.blogmanager.models.Blog;
import com.axsos.blogmanager.models.Comment;
import com.axsos.blogmanager.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comment,Long> {

    List<Comment> findAll();
    List<Comment> findByBlog(Blog blog);

}
