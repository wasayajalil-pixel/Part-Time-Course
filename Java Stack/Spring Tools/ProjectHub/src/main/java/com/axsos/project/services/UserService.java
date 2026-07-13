package com.axsos.project.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.axsos.project.models.LoginUser;
import com.axsos.project.models.User;
import com.axsos.project.repositories.UserRepository;

@Service
public class UserService {

	private static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5 MB
	private static final Set<String> ALLOWED_IMAGE_TYPES = Set.of("image/jpeg", "image/png", "image/gif", "image/webp");
	private static final Path UPLOAD_DIRECTORY = Paths.get("uploads", "profiles");
	private final UserRepository userRepository;
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	 public User register(User newUser, BindingResult result) {
	        Optional<User> existingUser = userRepository.findByEmail(newUser.getEmail());

	        if (existingUser.isPresent()) {
	            result.rejectValue("email", "Unique", "This email is already registered");
	        }

	        if (newUser.getPassword() != null && !newUser.getPassword().equals(newUser.getConfirm())) {
	            result.rejectValue("confirm", "Matches", "Passwords must match");
	        }

	        // A person is 18 only when their birthday is on or before today's date minus 18 years.
	        if (newUser.getBirthday() != null
	                && newUser.getBirthday().isAfter(LocalDate.now().minusYears(18))) {
	            result.rejectValue("birthday", "Age", "You must be at least 18 years old");
	        }

	        validateProfileImage(newUser.getProfileImageFile(), result);

	        if (result.hasErrors()) {
	            return null;
	        }

	        try {
	            String imageFilename = saveProfileImage(newUser.getProfileImageFile());
	            newUser.setProfileImage(imageFilename);
	        } catch (IOException exception) {
	            result.rejectValue("profileImageFile", "Upload", "Could not save the profile image");
	            return null;
	        }

	        String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
	        newUser.setPassword(hashedPassword);
	        return userRepository.save(newUser);
	    }

	    private void validateProfileImage(MultipartFile image, BindingResult result) {
	        if (image == null || image.isEmpty()) {
	            result.rejectValue("profileImageFile", "Required", "Profile image is required");
	            return;
	        }

	        if (image.getSize() > MAX_IMAGE_SIZE) {
	            result.rejectValue("profileImageFile", "Size", "Profile image must be 5 MB or smaller");
	        }

	        if (image.getContentType() == null || !ALLOWED_IMAGE_TYPES.contains(image.getContentType())) {
	            result.rejectValue("profileImageFile", "Type", "Use a JPG, PNG, GIF, or WEBP image");
	        }
	    }

	    private String saveProfileImage(MultipartFile image) throws IOException {
	        Files.createDirectories(UPLOAD_DIRECTORY);

	        String originalFilename = image.getOriginalFilename();
	        String extension = "";
	        if (originalFilename != null && originalFilename.contains(".")) {
	            extension = originalFilename.substring(originalFilename.lastIndexOf('.')).toLowerCase();
	        }

	        String generatedFilename = UUID.randomUUID() + extension;
	        Path target = UPLOAD_DIRECTORY.resolve(generatedFilename).normalize();
	        Files.copy(image.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
	        return generatedFilename;
	    }

	    public User login(LoginUser loginUser, BindingResult result) {
	        Optional<User> potentialUser = userRepository.findByEmail(loginUser.getEmail());

	        if (potentialUser.isEmpty()) {
	            result.rejectValue("email", "NotFound", "Email not found");
	            return null;
	        }

	        User user = potentialUser.get();
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
