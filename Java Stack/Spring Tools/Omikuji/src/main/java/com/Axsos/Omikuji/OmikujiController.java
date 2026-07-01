package com.Axsos.Omikuji;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class OmikujiController {

	@RequestMapping("/omikuji")
	public String index() {
		return "index";
	}

	@PostMapping("/omikuji/process")
	public String form(
//			receives the data from user to send it to the  server
			@RequestParam("number") int number,
			@RequestParam("city") String city,
            @RequestParam("person") String person,
            @RequestParam("hobby") String hobby,
            @RequestParam("thing") String thing,
            @RequestParam("nice") String nice,
			HttpSession session
			) {
//		Save the data in session
		session.setAttribute("number", number);
        session.setAttribute("city", city);
        session.setAttribute("person", person);
        session.setAttribute("hobby", hobby);
        session.setAttribute("thing", thing);
        session.setAttribute("nice", nice);
        
		return "redirect:/omikuji/show";
	}

//	Show the fortune page
//	takes data from session Then sends it to JSP using model
	@RequestMapping("omikuji/show")
    public String show(HttpSession session, Model model) {
        model.addAttribute("number", session.getAttribute("number"));
        model.addAttribute("city", session.getAttribute("city"));
        model.addAttribute("person", session.getAttribute("person"));
        model.addAttribute("hobby", session.getAttribute("hobby"));
        model.addAttribute("thing", session.getAttribute("thing"));
        model.addAttribute("nice", session.getAttribute("nice"));
        
     return "show";

 }
}
