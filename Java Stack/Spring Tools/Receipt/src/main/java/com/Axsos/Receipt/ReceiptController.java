package com.Axsos.Receipt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReceiptController {
    @RequestMapping("/")
	public String index(Model model) {
    	model.addAttribute("name","Jalil Wasaya");
    	model.addAttribute("itemName","Laptop");
    	model.addAttribute("price",1500);
    	model.addAttribute("desc","HP gaming laptop");
    	model.addAttribute("vendor","HP Store");
		return "index.jsp";
    	
    	
    }
}
