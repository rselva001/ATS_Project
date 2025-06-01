package com.example.Final_Project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Allow anyone to create a user (POST /api/users)
                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                // Require Admin role for all other /api/users/** endpoints
                .requestMatchers("/api/users/**").hasRole("Admin")
                .requestMatchers("/api/jobs/**").hasAnyRole("Admin", "Recruiter")
                .requestMatchers("/api/applications/**").hasAnyRole("Admin", "Recruiter", "HiringManager")
                .requestMatchers("/api/interviews/**").hasAnyRole("Admin", "HiringManager", "Interviewer")
                .requestMatchers("/api/feedback/**").hasAnyRole("Admin", "HiringManager", "Interviewer")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())

            // ✅ Fix: Add logout configuration to prevent NullPointerException
            .logout(logout -> logout
                .logoutUrl("/logout") // default is /logout
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(200); // Send 200 OK on logout
                })
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            );

        return http.build();
    }
}
