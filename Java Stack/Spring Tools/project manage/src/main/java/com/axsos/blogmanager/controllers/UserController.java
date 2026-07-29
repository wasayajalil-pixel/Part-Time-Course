package com.axsos.blogmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.axsos.blogmanager.models.LoginUser;
import com.axsos.blogmanager.models.User;
import com.axsos.blogmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;

// @Controller: an MVC controller that returns JSP views
@Controller
public class UserController {

    // Inject the user service (controller -> service -> repository)
    @Autowired
    private UserService userServ;

    // GET / : the login and registration page
    @GetMapping("/")
    public String index(Model model) {

        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());

        return "index";
    }

    // POST /register : handles the registration form
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session) {

        // Call the register method in the service to do the
        // extra validations and create a new user if no errors
        User user = userServ.register(newUser, result);

        if (result.hasErrors()) {
            // Be sure to send in the empty LoginUser before
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }

        // No errors!
        // Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("id", user.getId());

        return "redirect:/dashboard";
    }

    // POST /login : handles the login form
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session) {

        // The service checks: does a user with that email exist in the
        // database? If so, is the password the right password for that email?
        User user = userServ.login(newLogin, result);

        if (result.hasErrors()) {
            // Be sure to send in the empty User before
            // re-rendering the page.
            model.addAttribute("newUser", new User());
            return "index";
        }

        // No errors!
        // Store their ID from the DB in session,
        // in other words, log them in.
        session.setAttribute("id", user.getId());

        return "redirect:/dashboard";
    }

    // GET /logout : upon logging out,
    // the user's session should be terminated.
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate() destroys the session (removes the userId),
        // so /home will redirect them back to the login page.
        session.invalidate();
        return "redirect:/";
    }
}