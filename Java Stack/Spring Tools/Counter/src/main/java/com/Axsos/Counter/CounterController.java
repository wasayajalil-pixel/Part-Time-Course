package com.Axsos.Counter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class CounterController {
	
	@RequestMapping("/")
	public String index(HttpSession session) {
		Integer count = (Integer) session.getAttribute("count");
		if(count == null) {
			count = 0;
		}
		count++;
		session.setAttribute("count", count);
		
		
		return "index";
	}	
	@RequestMapping("/counter")
	public String counter(HttpSession mysession ,Model model) {
		Integer count = (Integer) mysession.getAttribute("count");
		model.addAttribute("count", count);
		return "counter";
	}
  
}
