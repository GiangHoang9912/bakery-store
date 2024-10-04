package com.example.bakery.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bakery.models.User;
import com.example.bakery.repository.UserRepository;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String email, String password) {
        logger.info("Attempting to authenticate user with email: {}", email);
        
        User user = userRepository.findByEmail(email)
                .orElse(null);
        
        if (user == null) {
            logger.warn("User not found with email: {}", email);
            throw new RuntimeException("User not found");
        }
        
        logger.info("User found: {}", user);
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            logger.warn("Invalid password for user: {}", email);
            throw new RuntimeException("Invalid password");
        }
        
        return user;
    }
}