package com.axsos.game.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.axsos.game.models.LoginUser;
import com.axsos.game.models.User;
import com.axsos.game.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	private final UserService userService;

    // Dependency Injection
	public UserController(UserService userService) {
		this.userService = userService;
	}
	// Show login/register page
	//@ModelAttribute connects a Java object to the JSP form.
	@GetMapping("/")
	public String index(
			@ModelAttribute("newUser") User newUser,
			@ModelAttribute("newLogin") LoginUser newLogin) {
		return "login.jsp";
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
            return "login.jsp";
        }

        // Store logged-in user id in session
        session.setAttribute("userId", user.getId());

        return "redirect:/dashboard";
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
            return "login.jsp";
        }

        // Store user id in session
        session.setAttribute("userId", user.getId());

        return "redirect:/dashboard";
    }
    
 
	
    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // Clear session
        session.invalidate();

        return "redirect:/";
    }
}


