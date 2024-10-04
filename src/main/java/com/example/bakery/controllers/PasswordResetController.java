package com.example.bakery.controllers;

import com.example.bakery.dto.PasswordResetRequestDto;
import com.example.bakery.dto.PasswordResetConfirmDto;
import com.example.bakery.services.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password-reset")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @Autowired
    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/request")
    public ResponseEntity<?> requestPasswordReset(@RequestBody PasswordResetRequestDto request) {
        passwordResetService.createPasswordResetTokenForUser(request.getEmail());
        return ResponseEntity.ok("Password reset link sent to email");
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmPasswordReset(@RequestBody PasswordResetConfirmDto confirmDto) {
        passwordResetService.validatePasswordResetToken(confirmDto.getToken());
        passwordResetService.resetPassword(confirmDto.getToken(), confirmDto.getNewPassword());
        return ResponseEntity.ok("Password has been reset successfully");
    }
}