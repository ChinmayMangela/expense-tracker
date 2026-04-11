package com.chinmay.expense_tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    @Bean // This method returns the object that Spring Security uses to lock/unlock your API
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF
                // Since we are building an API (and using Postman), we don't need CSRF protection.
                // If we didn't do this, every POST request would be rejected by default.
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Define Authorization Rules
                .authorizeHttpRequests(auth -> auth
                        // Anyone is allowed to reach the /users endpoint (needed for sign-up/registration)
                        .requestMatchers("/users/**").permitAll()

                        // For any other URL (like /expenses), the user MUST be logged in
                        .anyRequest().authenticated()
                )

                // 3. Enable Basic Authentication
                // This allows us to send credentials (username/password) in the "Authorization" header.
                // It's the standard way Postman sends credentials for simple testing.
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }





    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}