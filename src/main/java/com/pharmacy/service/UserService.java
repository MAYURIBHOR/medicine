package com.pharmacy.service;

import com.pharmacy.model.User;
import com.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean isValidPassword(String password) {
        // Password must be at least 8 chars, include 1 special, 1 upper, 1 lower
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    public String getPasswordValidationMessage(String password) {
        if (password.length() < 8)
            return "Password must be at least 8 characters.";
        if (!password.matches(".*[A-Z].*"))
            return "Password must contain at least one uppercase letter.";
        if (!password.matches(".*[a-z].*"))
            return "Password must contain at least one lowercase letter.";
        if (!password.matches(".*[@$!%*?&].*"))
            return "Password must contain at least one special character (@$!%*?&).";
        return "";
    }

    public User registerUser(String email, String password, String role) {
        if (!isValidPassword(password))
            return null;
        User user = User.builder()
                .username(email)
                .password(passwordEncoder.encode(password))
                .role(role)
                .active(true)
                .build();
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}