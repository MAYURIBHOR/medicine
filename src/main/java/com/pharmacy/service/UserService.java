package com.pharmacy.service;

import com.pharmacy.model.User;
import com.pharmacy.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection (Spring automatically injects beans)
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Validate password and return message
    public String getPasswordValidationMessage(String password) {
        if (password == null || password.isEmpty())
            return "Password cannot be empty.";
        if (password.length() < 8)
            return "Password must be at least 8 characters.";
        if (!password.matches(".*[A-Z].*"))
            return "Password must have at least one uppercase letter.";
        if (!password.matches(".*[a-z].*"))
            return "Password must have at least one lowercase letter.";
        if (!password.matches(".*\\d.*"))
            return "Password must have at least one number.";
        if (!password.matches(".*[@$!%*?&].*"))
            return "Password must have at least one special character.";
        return "";
    }

    // Find user by username (email)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Register new user
    public void registerUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
    }
}
