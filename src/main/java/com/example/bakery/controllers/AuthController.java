package com.example.bakery.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.bakery.config.JwtTokenProvider;
import com.example.bakery.dto.LoginRequest;
import com.example.bakery.dto.LoginResponse;
import com.example.bakery.dto.RegisterRequest;
import com.example.bakery.models.User;
import com.example.bakery.services.AuthService;
import com.example.bakery.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final JwtTokenProvider tokenProvider;
    private final AuthService authService;
    private final UsersService usersService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
            AuthService authService, UsersService usersService) {
        this.tokenProvider = tokenProvider;
        this.authService = authService;
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities());

            logger.info(authentication.toString());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            return ResponseEntity
                    .ok(new LoginResponse(jwt, user.getRole().toString(), user.getFullname(), user.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            User registeredUser = usersService.registerUser(registerRequest);
            return ResponseEntity.ok(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}