package com.example.bakery.services;

import com.example.bakery.dto.RegisterRequest;
import com.example.bakery.models.User;
import com.example.bakery.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private static final Logger logger = LoggerFactory.getLogger(UsersService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequest registerRequest) {
        try {
            User newUser = new User();
            newUser.setFullname(registerRequest.getFullname());
            newUser.setEmail(registerRequest.getEmail());
            newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            newUser.setPhone(registerRequest.getPhone());
            newUser.setRole(1);

            return userRepository.save(newUser);
        } catch (Exception e) {
            logger.error("Error registering user", e);
            throw new RuntimeException("Error registering user", e);
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
