package com.axsos.blogmanager.controllers;

import com.axsos.blogmanager.models.Blog;
import com.axsos.blogmanager.models.Comment;
import com.axsos.blogmanager.models.User;
import com.axsos.blogmanager.services.BlogServices;
import com.axsos.blogmanager.services.CommentService;
import com.axsos.blogmanager.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommentController {
private final CommentService commentService;
private final UserService userService;
private  final BlogServices blogServices;
public CommentController(CommentService commentService ,UserService userService,BlogServices blogServices){
    this.commentService = commentService;
    this.blogServices = blogServices;
    this.userService = userService;
}

    @RequestMapping("/details/{blog_id}/comments")
    public String showComments(@PathVariable("blog_id")Long blog_id, HttpSession session, Model model,@ModelAttribute("comment") Comment comment) {
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }
        Long id = (Long) session.getAttribute("id");



        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("MyComments", commentService.getAllComments());
        model.addAttribute("blog",blogServices.getSingleBlogById(blog_id));
        return "comments";
    }

    @PostMapping("/details/{blog_id}/comments/new")
    public String createComment(@PathVariable("blog_id")Long blog_id,HttpSession session, @Valid @ModelAttribute("comment") Comment comment, BindingResult result, Model model){
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }
        Long id = (Long) session.getAttribute("id");


        if(result.hasErrors()){
            model.addAttribute("comment", comment);
            model.addAttribute("MyComments",commentService.getAllComments());
            model.addAttribute("blog",blogServices.getSingleBlogById(blog_id));
            model.addAttribute("user",userService.findUserById(id));

            return "comments";
        }
        User user = userService.findUserById(id);
        comment.setUser(user);
        model.addAttribute("user",userService.findUserById(id));
        commentService.createCommentCall(comment);
        return "redirect:/details/{blog_id}/comments";
    }

}
