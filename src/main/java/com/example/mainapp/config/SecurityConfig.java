package com.example.mainapp.config;

import com.example.mainapp.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl service;

    public SecurityConfig(UserDetailsServiceImpl service) {
        this.service = service;
    }
}
