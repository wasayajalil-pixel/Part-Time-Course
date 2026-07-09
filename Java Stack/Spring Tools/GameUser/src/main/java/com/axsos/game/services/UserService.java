package com.axsos.game.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.mindrot.jbcrypt.BCrypt;
import com.axsos.game.models.LoginUser;
import com.axsos.game.models.User;
import com.axsos.game.repositories.UserRepository;

//Business Logic
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
        Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());

        if (potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "This email is already used");
        }

        // Check password match
        if (!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "Passwords must match");
        }

        // Check age 18+
        if (!newUser.isAdult()) {
            result.rejectValue("birthday", "Age", "You must be at least 18 years old");
        }

        // If there are errors, return null
        if (result.hasErrors()) {
            return null;
        }

        // Hash password before saving
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);

        // Save user
        return userRepository.save(newUser);
    }

    // Login user
    public User login(LoginUser loginUser, BindingResult result) {

        // Find user by email
        Optional<User> potentialUser = userRepository.findByEmail(loginUser.getEmail());

        if (potentialUser.isEmpty()) {
            result.rejectValue("email", "NotFound", "Invalid email or password");
            return null;
        }

        User user = potentialUser.get();

        // Check password
        if (!BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid email or password");
            return null;
        }

        return user;
    }

    // Find user by id
    public User findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        return null;
    }
}

