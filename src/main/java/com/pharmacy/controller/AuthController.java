package com.pharmacy.controller;

import com.pharmacy.model.User;
import com.pharmacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error != null ? "Invalid email or password." : null);
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        String validationMsg = userService.getPasswordValidationMessage(user.getPassword());
        if (!validationMsg.isEmpty()) {
            model.addAttribute("error", validationMsg);
            return "register";
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Email already registered.");
            return "register";
        }
        userService.registerUser(user.getUsername(), user.getPassword(), user.getRole());
        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }
}
