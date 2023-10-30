package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.masai.exception.EmailAlreadyExistsException;
import com.masai.exception.UsernameAlreadyExistsException;
import com.masai.model.User;
import com.masai.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private EmailService emailService;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	
	public void registerUser(User user) {
        validateUser(user);

        // Hash the password before saving it to the database
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        User savedUser = userRepository.save(user);

        // Send a welcome email to the user
        emailService.sendWelcomeEmail(savedUser.getEmail(), savedUser.getUsername());
    }
	
	private void validateUser(User user) {
        // Check if username is already in use
        Optional<User> existingUsername = userRepository.findByUsername(user.getUsername());
        if (existingUsername.isPresent()) {
            throw new UsernameAlreadyExistsException("Username is already in use.");
        }

        // Check if email is already in use
        Optional<User> existingEmail = userRepository.findByEmail(user.getEmail());
        if (existingEmail.isPresent()) {
            throw new EmailAlreadyExistsException("Email is already in use.");
        }

        // You can add more validation logic here if needed
    }
}
