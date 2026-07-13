package com.axsos.gameM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.axsos.gameM.models.User;
import com.axsos.gameM.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/{id}")
    public String profile(
            @PathVariable("id") Long id,
            Model model,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/";
        }

        User loggedUser = userService.findUser(userId);
        User profileUser = userService.findUser(id);

        if (profileUser == null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("profileUser", profileUser);

        return "profile.jsp";
    }
}