package com.axsos.java.exam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.axsos.java.exam.models.Blogs;
import com.axsos.java.exam.models.User;
import com.axsos.java.exam.services.BlogsService;
import com.axsos.java.exam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BlogsController {
	private final BlogsService blogsService;
	private final UserService userService;

	public BlogsController(BlogsService blogsService, UserService userService) {
		this.blogsService = blogsService;
		this.userService = userService;
	}

	// Get the logged-in user ID from the session
	private Long currentUserId(HttpSession session) {
		return (Long) session.getAttribute("userId");
	}

	@GetMapping("/dashboard")
	public String blogs(Model model, HttpSession session) {

		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			return "redirect:/";
		}
		model.addAttribute("loggedUser", userService.findUser(userId));
		model.addAttribute("blogs", blogsService.allBlogs());

		return "dashboard.jsp";
	}

	// display the Create blog page
	@GetMapping("/blogs/new")
	public String newBlogs(@ModelAttribute("blogs") Blogs blogs, HttpSession session) {

		if (currentUserId(session) == null) {
			return "redirect:/";
		}

		return "newBlogs.jsp";
	}

	@PostMapping("/blogs")
	public String createBlogs(@Valid @ModelAttribute("blogs") Blogs blogs, BindingResult result, HttpSession session) {

		Long userId = currentUserId(session);

		if (userId == null) {
			return "redirect:/";
		}

		if (result.hasErrors()) {
			return "newBlogs.jsp";
		}

		User loggedUser = userService.findUser(userId);

		blogs.setPostedBy(loggedUser);

		blogsService.createBlogs(blogs);

		return "redirect:/dashboard";
	}

	@GetMapping("/blogs/{id}")
	public String showBlogs(@PathVariable("id") Long id, Model model, HttpSession session) {

		if (currentUserId(session) == null) {
			return "redirect:/";
		}

		Blogs blogs = blogsService.findBlogs(id);

		if (blogs == null) {
			return "redirect:/dashboard";
		}

		model.addAttribute("blogs", blogs);
		model.addAttribute("userId", currentUserId(session));

		return "details.jsp";
	}

	// Show edit project page
	@GetMapping("/blogs/{id}/edit")
	public String editPage( @PathVariable("id") Long id, Model model, HttpSession session) {

		Long userId = currentUserId(session);

		if (userId == null) {
			return "redirect:/";
		}

	      Blogs blogs = blogsService.findBlogs(id);

	        if (blogs == null) {
	            return "redirect:/blogs";
	        }
	        
	        if (!blogs.getPostedBy().getId().equals(userId)) {
	        	return "redirect:/dashboard";
	        }

	        model.addAttribute("blogs", blogs);

			return "edit.jsp";
	
	}
	// Update an existing project
		@PutMapping("/blogs/{id}")
		public String update(
				@PathVariable("id") Long id,
				@Valid @ModelAttribute("blogs") Blogs blogs,
				BindingResult result,
				HttpSession session) {

			Long userId = currentUserId(session);

			if (userId == null) {
				return "redirect:/";
			}

			Blogs existingBlogs = blogsService.findBlogs(id);

			if (existingBlogs.getPostedBy().getId().equals(userId)) {
	            return "redirect:/dashboard";
	        }

	        if (result.hasErrors()) {
	            return "edit.jsp";
	        }

	        // Keep same id and same creator
	        blogs.setId(id); // add setId() in Game model
	        blogs.setPostedBy(existingBlogs.getPostedBy());

	        blogsService.updateBlogs(blogs);

	        return "redirect:/blogs/" + id;
		}

		// Delete a project
		@GetMapping("/delete/blogs/{id}")
		public String delete(
				@PathVariable("id") Long id,
				HttpSession session) {

			Long userId = (Long) session.getAttribute("userId");
			System.out.println(userId);
			if (userId == null) {
				return "redirect:/";
			}

			Blogs blogs = blogsService.findBlogs(id);
			//System.out.println("blog user id" + blogs.getPostedBy().getId().equals(userId));
			 if (blogs.getPostedBy().getId().equals(userId)) {
		            blogsService.deleteBlogs(id);
			}

			return "redirect:/dashboard";
		}

}
