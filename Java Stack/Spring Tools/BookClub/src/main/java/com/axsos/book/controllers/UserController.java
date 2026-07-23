package com.axsos.book.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.axsos.book.models.LoginUser;
import com.axsos.book.models.User;
import com.axsos.book.services.UserService;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// Show login/register page
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("userId") != null) {
            return "redirect:/books";
        }
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    } 
    
    //register user
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("newUser") User newUser,
            BindingResult result,
            Model model,
            HttpSession session) {

        User registeredUser = userService.register(newUser, result);

        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }

        session.setAttribute("userId", registeredUser.getId());
        return "redirect:/books";
    }
    
    //login user
    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("newLogin") LoginUser newLogin,
            BindingResult result,
            Model model,
            HttpSession session) {

        User loggedUser = userService.login(newLogin, result);

        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }

        session.setAttribute("userId", loggedUser.getId());
        return "redirect:/books";
    }

    //logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
	
