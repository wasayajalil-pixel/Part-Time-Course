package com.axsos.blogmanager.controllers;

import com.axsos.blogmanager.models.Blog;
import com.axsos.blogmanager.models.User;
import com.axsos.blogmanager.services.BlogServices;
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
public class BlogController {
private final BlogServices blogServices;
    private final UserService userService;

    public BlogController(BlogServices blogServices, UserService userService){
    this.blogServices = blogServices;
        this.userService = userService;
    }

@RequestMapping("/dashboard")
public String showBlogs(HttpSession session, Model model){
    if (session.getAttribute("id") == null) {
        return "redirect:/";
    }
    Long id = (Long) session.getAttribute("id");
    User currentUser = userService.findUserById(id);
    model.addAttribute("user", currentUser);
    model.addAttribute("availableBlogs", blogServices.getAvailableBlogs(currentUser));
    model.addAttribute("MyBlogs", blogServices.getMyBlogs(currentUser));
    return "dashboard";
}

@RequestMapping("/create")
public String createBlogPage(HttpSession session,Model model){
    if (session.getAttribute("id") == null) {
        return "redirect:/";
    }
    Long id = (Long) session.getAttribute("id");
    model.addAttribute("user", userService.findUserById(id));
    model.addAttribute("blog",new Blog());
        return "createblog";
}

@PostMapping("/create/new")
    public String createBlog(HttpSession session, @Valid @ModelAttribute("blog") Blog blog, BindingResult result, Model model){
    if (session.getAttribute("id") == null) {
        return "redirect:/";
    }
    if(result.hasErrors()){
        model.addAttribute("blog", blog);
        return "createblog";
    }
    Long id = (Long) session.getAttribute("id");
        User user = userService.findUserById(id);
        blog.setUser(user);
        model.addAttribute("user",userService.findUserById(id));
        blogServices.createCallBlog(blog);
        return "redirect:/dashboard";
}

@RequestMapping("/edit/{blog_id}")
public String updateBlogPage(@PathVariable("blog_id")Long blog_id,HttpSession session,Model model){
        if (session.getAttribute("id") == null) {
        return "redirect:/";
    }
    Blog blog = blogServices.getSingleBlogById(blog_id);
    Long sessionId = (Long) session.getAttribute("id");
    if (!blog.getUser().getId().equals(sessionId)) {
        return "redirect:/dashboard";
    }

    Long id = (Long) session.getAttribute("id");

    model.addAttribute("blog", blog);
    model.addAttribute("user",userService.findUserById(id));
    return "updateblog";
}

    @PostMapping("/edit/{blog_id}/update")
    public String updateBlog(HttpSession session, @PathVariable("blog_id") Long blog_id,
                                @Valid @ModelAttribute("blog") Blog blog, BindingResult result, Model model){
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }
        Long sessionId = (Long) session.getAttribute("id");
        Blog existing = blogServices.getSingleBlogById(blog_id);
        if (!existing.getUser().getId().equals(sessionId)) {
            return "redirect:/dashboard";
        }
        if (result.hasErrors()){
            model.addAttribute("blog", blog);
            return "updateblog";
        }
        blogServices.updateCallBlog(blog_id, blog.getTitle(), blog.getCategory(), blog.getContent());
        return "redirect:/dashboard";
    }
@RequestMapping("/delete/{blog_id}")
public  String deleteBlog(@PathVariable("blog_id") Long blog_id,HttpSession session){
    if (session.getAttribute("id") == null) {
        return "redirect:/";
    }
    Blog blog = blogServices.getSingleBlogById(blog_id);
    Long sessionId = (Long) session.getAttribute("id");
    if (!blog.getUser().getId().equals(sessionId)) {
        return "redirect:/dashboard";
    }
    blogServices.deleteCallBlog(blog_id);
    return "redirect:/dashboard";
    }
@RequestMapping("details/{blog_id}")
public String showBlog(@PathVariable("blog_id")Long blog_id,HttpSession session,Model model) {
    if (session.getAttribute("id") == null) {
        return "redirect:/";
    }
    Long id = (Long) session.getAttribute("id");
    model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("blog", blogServices.getSingleBlogById(blog_id));
        return "blogdetails";
    }
}
