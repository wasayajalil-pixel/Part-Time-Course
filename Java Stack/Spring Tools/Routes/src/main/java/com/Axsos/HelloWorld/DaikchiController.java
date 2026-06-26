package com.Axsos.HelloWorld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/daikchi")
public class DaikchiController {
	
	@RequestMapping("")
	public String displayPage() {
		return "Welcome";
	}
	
	@RequestMapping("/today")
	public String displayToday() {
		return "Today you will find luck in all your endeavors!";
	}
	
	@RequestMapping("/tomorrow")
	public String displayTomorrow() {
		return "Tomorrow, an opportunity will arise, so be sure to be open to new ideas!";

	}

}
