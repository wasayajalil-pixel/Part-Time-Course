package com.Axsos.HelloHuman;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
		if (times == null || times < 1) {
			return greeting;
		}

		// Repeat greeting
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < times; i++) {
			result.append(greeting);

			if (i != times - 1) {
				result.append("\n");
			}
		}

		return result.toString();
	}

}
