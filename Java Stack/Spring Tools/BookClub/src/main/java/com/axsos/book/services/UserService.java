package com.axsos.book.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.axsos.book.models.LoginUser;
import com.axsos.book.models.User;
import com.axsos.book.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	// Register a new user
	public User register(User newUser, BindingResult result) {

		// Check whether the email is already registered
		if (newUser.getEmail() != null && !newUser.getEmail().isBlank()) {
			Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());

			if (existingUser.isPresent()) {
				result.rejectValue("email", "Unique", "This email is already registered");
			}
		}

		// Check whether password and confirmation match
		if (newUser.getPassword() != null && newUser.getConfirm() != null
				&& !newUser.getPassword().equals(newUser.getConfirm())) {

			result.rejectValue("confirm", "Matches", "Passwords must match");
		}

		// Do not save the user when validation errors exist
		if (result.hasErrors()) {
			return null;
		}

		// Hash the password before saving
		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());

		newUser.setPassword(hashedPassword);

		return userRepository.save(newUser);
	}

	public User login(LoginUser loginUser, BindingResult result) {
		// Stop if @Valid found empty or invalid fields
		if (result.hasErrors()) {
			return null;
		}

		Optional<User> potentialUser = userRepository.findByEmail(loginUser.getEmail());
		// Email does not exist
		if (potentialUser.isEmpty()) {
			result.rejectValue("email", "NotFound", "Email not found");
			return null;
		}

		User user = potentialUser.get();
		// Password is incorrect
		if (!BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid password");
			return null;
		}

		return user;
	}

	public User findUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

}
