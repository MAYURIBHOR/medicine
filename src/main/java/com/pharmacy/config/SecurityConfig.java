package com.pharmacy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for H2 console
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // allow H2 console
                        .anyRequest().authenticated())
                .headers(headers -> headers.frameOptions(frame -> frame.disable())); // allow frames

        return http.build();
    }
}
