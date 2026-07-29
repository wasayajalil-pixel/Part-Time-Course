package com.axsos.blogmanager.services;

import com.axsos.blogmanager.models.Blog;
import com.axsos.blogmanager.models.User;
import com.axsos.blogmanager.repositires.BlogRepo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServices {
private final BlogRepo blogRepo;

public BlogServices(BlogRepo blogRepo){
    this.blogRepo = blogRepo;
}
//show all
 public List<Blog> getAllBlogs(){
    return blogRepo.findAll();
 }
// get single one by id
 public Blog getSingleBlogById(Long blog_id){
     Optional<Blog> optionalBlog = blogRepo.findById(blog_id);
     if(optionalBlog.isPresent()){
             return optionalBlog.get();
     }
     return null;

 }
// create blog
 public void createCallBlog(Blog blog){
    blogRepo.save(blog);
 }
// delete blog
 public void deleteCallBlog(Long blog_id){
    blogRepo.deleteById(blog_id);
 }
// update blog
 public Blog updateCallBlog(Long blog_id, String title, String category, String content){
    Blog blog = getSingleBlogById(blog_id);
     if (blog == null) {
         return null;
     }
    blog.setTitle(title);
    blog.setCategory(category);
    blog.setContent(content);
    return blogRepo.save(blog);
 }
    // ===== Blog  FEATURE =====



    // Marks a blog as borrowed by the logged-in user.
    public void borrowBlog(Long blog_id, User currentUser){
        Blog blog = getSingleBlogById(blog_id);
        if (blog == null) {
            return;
        }
        blog.setBorrower(currentUser);
        blogRepo.save(blog);
    }

    // Clears a blog's borrower, making it available again.
    public void returnBlog(Long blog_id){
        Blog blog = getSingleBlogById(blog_id);
        if (blog == null) {
            return;
        }
        blog.setBorrower(null);
        blogRepo.save(blog);
    }
    // All blogs EXCLUDING the ones the logged-in user created.
    public List<Blog> getAvailableBlogs(User currentUser){
        return blogRepo.findByUserNot(currentUser);
    }

    // Blogs the logged-in user created themselves.
    public List<Blog> getMyBlogs(User currentUser){
        return blogRepo.findByUser(currentUser);
    }


}


