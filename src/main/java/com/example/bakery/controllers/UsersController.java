package com.example.bakery.controllers;

import com.example.bakery.dto.RegisterRequest;
import com.example.bakery.models.User;
import com.example.bakery.services.UsersService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerRequest) {
        User registeredUser = usersService.registerUser(registerRequest);
        return ResponseEntity.ok(registeredUser);
    }
}
