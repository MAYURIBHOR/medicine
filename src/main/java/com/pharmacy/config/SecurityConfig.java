package com.pharmacy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for H2 console
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())) // Allow frames
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Allow H2 console
                        .anyRequest().authenticated() // Secure other endpoints
                )
                .formLogin(form -> form.permitAll()) // Enable login form
                .httpBasic(httpBasic -> {
                }); // Enable HTTP Basic (optional)

        return http.build();
    }
}
