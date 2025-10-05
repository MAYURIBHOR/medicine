package com.pharmacy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. In-Memory User Definition
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("admin@pharmacy.com")
                .password(passwordEncoder.encode("password123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    // 3. Security Filter Chain (The core security rules)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // FIX 1: Disable CSRF (for form simplicity and H2)
                .csrf(csrf -> csrf.disable())

                // FIX 2: Allow frames from same origin for H2 Console
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))

                // Authorize Requests
                .authorizeHttpRequests(auth -> auth

                        // --- PUBLIC ACCESS RULES (No login required) ---
                        // Allow H2 console, static assets (CSS/JS), login/register pages
                        .requestMatchers("/h2-console/**", "/css/**", "/js/**", "/login", "/register").permitAll()

                        // --- AUTHENTICATED ACCESS RULES (SIMPLIFICATION FIX) ---
                        // Authenticate any other request. This is the simplest rule and should cover
                        // all dashboards.
                        .anyRequest().authenticated())

                // Configure Form Login
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll())

                // Log out configuration
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}