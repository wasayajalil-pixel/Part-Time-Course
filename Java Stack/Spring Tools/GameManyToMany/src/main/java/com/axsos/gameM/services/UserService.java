package com.axsos.gameM.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.axsos.gameM.models.LoginUser;
import com.axsos.gameM.models.User;
import com.axsos.gameM.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
    // Register user
    public User register(User newUser, BindingResult result) {

        // Check if email already exists
        if (userRepository.existsByEmail(newUser.getEmail())) {
            result.rejectValue("email", "Unique", "Email already exists");
        }

        // Check password and confirm password
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "Matches", "Passwords must match");
        }

        // Check age >= 18
        if (newUser.getDateOfBirth() != null) {

            LocalDate birthDate = newUser.getDateOfBirth()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            int age = Period.between(birthDate, LocalDate.now()).getYears();

            if (age < 18) {
                result.rejectValue("dateOfBirth", "Age", "User must be 18 years or older");
            }
        }

        // If there are errors, stop registration
        if (result.hasErrors()) {
            return null;
        }

        // Hash password before saving
        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashedPassword);

        // Save user in database
        return userRepository.save(newUser);
    }

    // Login user
    public User login(LoginUser loginUser, BindingResult result) {

        // Try to find user by email
        Optional<User> optionalUser = userRepository.findByEmail(loginUser.getEmail());

        if (optionalUser.isEmpty()) {
            result.rejectValue("email", "NotFound", "Invalid email or password");
            return null;
        }

        User user = optionalUser.get();

        // Check encrypted password
        if (!BCrypt.checkpw(loginUser.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid email or password");
            return null;
        }

        return user;
    }

    // Find user by id
    public User findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
	

}
