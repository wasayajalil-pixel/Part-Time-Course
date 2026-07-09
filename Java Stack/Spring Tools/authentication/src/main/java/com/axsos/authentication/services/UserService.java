package com.axsos.authentication.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.axsos.authentication.models.LoginUser;
import com.axsos.authentication.models.User;
import com.axsos.authentication.repositories.UserRepository;

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

        // Check if password and confirm password match
        if (!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "Matches", "Passwords do not match");
        }

        // If there are any validation errors, stop here
        if (result.hasErrors()) {
            return null;
        }

        // Hash password before saving to database
        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashedPassword);

        // Save user in database
        return userRepository.save(newUser);
    }

    // Login user
    public User login(LoginUser newLogin, BindingResult result) {

        // Find user by email
        Optional<User> potentialUser = userRepository.findByEmail(newLogin.getEmail());

        if (!potentialUser.isPresent()) {
            result.rejectValue("email", "NotFound", "Email not found");
            return null;
        }

        User user = potentialUser.get();

        // Check password with hashed password
        if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid password");
            return null;
        }

        return user;
    }

    // Find user by id
    public User findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }
}