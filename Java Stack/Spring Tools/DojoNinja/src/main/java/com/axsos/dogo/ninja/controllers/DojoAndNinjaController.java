package com.axsos.dogo.ninja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.axsos.dogo.ninja.models.Dojo;
import com.axsos.dogo.ninja.models.Ninja;
import com.axsos.dogo.ninja.services.DojoService;
import com.axsos.dogo.ninja.services.NinjaService;

import jakarta.validation.Valid;

@Controller
public class DojoAndNinjaController {
private final DojoService dojoService;
private final NinjaService ninjaService;

public DojoAndNinjaController(DojoService dojoService, NinjaService ninjaService) {
	this.dojoService = dojoService;
	this.ninjaService = ninjaService;
}
@GetMapping("/")
public String index() {
	return "redirect:dojos/new";
}

@GetMapping("/dojos/new")
public String newDojo(@ModelAttribute("dojo") Dojo dojo) {
	return "newDojo.jsp";
}

@PostMapping("/dojos")
public String createDojo(
        @Valid @ModelAttribute("dojo") Dojo dojo,
        BindingResult result) {

    if (result.hasErrors()) {
        return "newDojo.jsp";
    }

    dojoService.createDojo(dojo);
    return "redirect:/ninjas/new";
}

@GetMapping("/ninjas/new")
public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
    model.addAttribute("dojos", dojoService.allDojos());
    return "newNinja.jsp";
}

@PostMapping("/ninjas")
public String createNinja(
        @Valid @ModelAttribute("ninja") Ninja ninja,
        BindingResult result,
        Model model) {

    if (result.hasErrors()) {
        model.addAttribute("dojos", dojoService.allDojos());
        return "newNinja.jsp";
    }

    ninjaService.createNinja(ninja);
    return "redirect:/dojos/" + ninja.getDojo().getId();
}

@GetMapping("/dojos/{id}")
public String showDojo(@PathVariable("id") Long id, Model model) {
    model.addAttribute("dojo", dojoService.findDojo(id));
    return "showDojo.jsp";
}

}
