package com.pharmacy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 1. Password Encoder Bean (Required for user authentication)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. In-Memory User Definition (The user that will log in)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        // --- LOGIN CREDENTIALS ---
        UserDetails user = User.builder()
                .username("admin@pharmacy.com") // <--- MUST be in email format for your form
                .password(passwordEncoder.encode("password123")) // Encoded password
                .roles("ADMIN")
                .build();
        // -------------------------

        return new InMemoryUserDetailsManager(user);
    }

    // 3. Security Filter Chain (The core security rules)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // *** FIX 1: DISABLE CSRF ***
                // This stops the login form from failing/refreshing due to a missing hidden
                // token.
                .csrf(csrf -> csrf.disable())

                // FIX 2: Allow frames from same origin for H2 Console
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))

                // Authorize Requests
                .authorizeHttpRequests(auth -> auth
                        // Allow access to H2 console, login page, and static assets
                        .requestMatchers("/h2-console/**", "/css/**", "/js/**", "/login", "/register").permitAll()

                        // All other requests require authentication
                        .anyRequest().authenticated())

                // Configure Form Login
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page
                        .defaultSuccessUrl("/dashboard", true) // Redirect to /dashboard on success
                        .permitAll())

                // Log out configuration
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}