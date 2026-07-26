package com.axsos.java.exam.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.axsos.java.exam.models.LoginUser;
import com.axsos.java.exam.models.User;
import com.axsos.java.exam.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	// Dependency Injection
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	// Register new user
	public User register(User newUser, BindingResult result) {
		// Check if email already exists
		Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());

		if (existingUser.isPresent()) {
			result.rejectValue("email", "Unique", "This email is already registered");
		}
		// Check password match
		if (newUser.getPassword() != null && !newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Passwords must match");
		}
		// If there are errors, return null
		if (result.hasErrors()) {
			return null;
		}
		// Hash password before saving
		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPassword);
		// Save user	
		return userRepository.save(newUser);
	}

	public User login(LoginUser loginUser, BindingResult result) {
		// Find user by email
		Optional<User> potentialUser = userRepository.findByEmail(loginUser.getEmail());

		if (potentialUser.isEmpty()) {
			result.rejectValue("email", "NotFound", "Email not found");
			return null;
		}
		//get the user object
		User user = potentialUser.get();
		// Check password
		if (!BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid password");
			return null;
		}

		return user;
	}
	// Find user by id
	public User findUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

}
