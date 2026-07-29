package com.axsos.blogmanager.controllers;


import com.axsos.blogmanager.models.Address;
import com.axsos.blogmanager.models.Blog;
import com.axsos.blogmanager.models.User;
import com.axsos.blogmanager.services.AddressServices;
import com.axsos.blogmanager.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddressContorller {
    private final AddressServices addressServices;
    private final UserService userService;
    public AddressContorller(AddressServices addressServices,UserService userService){
        this.addressServices = addressServices;
        this.userService = userService;
    }

    @RequestMapping("/address")
    public String createAddressPage(HttpSession session,Model model){
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }
        Long id = (Long) session.getAttribute("id");
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("address",new Address());

        return "address";
    }
    @PostMapping("/address/new")
    public String createAddress(HttpSession session, @Valid @ModelAttribute("address")Address address, BindingResult result, Model model){
        if (session.getAttribute("id") == null) {
            return "redirect:/";
        }
        if(result.hasErrors()){
            model.addAttribute("address", address);
            return "address";
        }
        Long id = (Long) session.getAttribute("id");
        User user = userService.findUserById(id);
        address.setUser(user);
        model.addAttribute("user",userService.findUserById(id));
        addressServices.createCallAddress(address);
        return "redirect:/dashboard";
    }
}
