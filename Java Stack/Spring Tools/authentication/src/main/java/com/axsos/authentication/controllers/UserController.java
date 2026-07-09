package com.axsos.authentication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.axsos.authentication.models.LoginUser;
import com.axsos.authentication.models.User;
import com.axsos.authentication.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    // Dependency Injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Show login and registration page
    @GetMapping("/")
    public String index(
            Model model,
            @ModelAttribute("newUser") User newUser,
            @ModelAttribute("newLogin") LoginUser newLogin) {

        return "index.jsp";
    }

    // Register user
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("newUser") User newUser,
            BindingResult result,
            Model model,
            HttpSession session) {

        User user = userService.register(newUser, result);

        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }

        // Save user id in session
        session.setAttribute("userId", user.getId());

        return "redirect:/home";
    }

    // Login user
    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute("newLogin") LoginUser newLogin,
            BindingResult result,
            Model model,
            HttpSession session) {

        User user = userService.login(newLogin, result);

        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }

        // Save user id in session
        session.setAttribute("userId", user.getId());

        return "redirect:/home";
    }

    // Home page after login
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("userId");

        // If user is not logged in, send him back to login page
        if (userId == null) {
            return "redirect:/";
        }

        User user = userService.findUser(userId);

        model.addAttribute("user", user);

        return "home.jsp";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // Clear session
        session.invalidate();

        return "redirect:/";
    }
}