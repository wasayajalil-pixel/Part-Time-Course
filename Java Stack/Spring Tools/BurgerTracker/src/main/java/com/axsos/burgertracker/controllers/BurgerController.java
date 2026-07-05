package com.axsos.burgertracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.axsos.burgertracker.models.Burger;
import com.axsos.burgertracker.services.BurgerService;

import jakarta.validation.Valid;

@Controller
public class BurgerController {

    private final BurgerService burgerService;
// Dependency Injection
    public BurgerController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }
//// Render home page with form and table
//ModelAttribute:Create an empty Burger object and send it to the form
    @GetMapping("/")
    public String index(@ModelAttribute("burger") Burger burger, Model model) {
        model.addAttribute("burgers", burgerService.allBurgers());
        return "index.jsp";
    }
// Valid:   checks the validations from the model
// BindingResult: must come directly after @Valid
    @PostMapping("/burgers")
    public String createBurger(
            @Valid @ModelAttribute("burger") Burger burger,
            BindingResult result,
            Model model) {
// If validation errors exist, render the same page again
        if (result.hasErrors()) {
            model.addAttribute("burgers", burgerService.allBurgers());
            return "index.jsp";
        }
//Redirect to prevent duplicate form submission
        burgerService.saveBurger(burger);
        return "redirect:/";
    }

    @GetMapping("/burgers/{id}/edit")
    public String editBurger(@PathVariable("id") Long id, Model model) {
        Burger burger = burgerService.findBurger(id);
        model.addAttribute("burger", burger);
        return "edit.jsp";
    }

    @PostMapping("/burgers/{id}/update")
    public String updateBurger(
            @Valid @ModelAttribute("burger") Burger burger,
            BindingResult result) {

        if (result.hasErrors()) {
            return "edit.jsp";
        }

        burgerService.saveBurger(burger);
        return "redirect:/";
    }
}