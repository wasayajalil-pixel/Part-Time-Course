package com.Axsos.HelloHuman;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
public class MainController {

	@GetMapping("/greeting")
	public String greetingMessage(@RequestParam(value = "first_name", required = false) String firstName,
			@RequestParam(value = "last_name", required = false) String lastName,
			@RequestParam(value = "times", required = false) Integer Times) {
		// Default name
		if (firstName == null || firstName.isEmpty()) {
			firstName = "human";
		}

		// Build greeting
		String greeting = "Hello " + firstName;

		// Add last name if provided
		if (lastName != null && !lastName.isEmpty()) {
			greeting += " " + lastName;
		}

		// If no "times" parameter, return once
		if (Times == null || Times < 1) {
			return greeting;
		}

		// Repeat greeting
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < Times; i++) {
			result.append(greeting);

			if (i != Times - 1) {
				result.append("\n");
			}
		}

		return result.toString();
	}
	@RequestMapping("/index")
	public String index(@PathVariable {"name"} String name) {
		return "hello" + name;
	}

}
