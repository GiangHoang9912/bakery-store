package com.example.bakery.controllers;

import com.example.bakery.dto.ContactSuccessDto;
import com.example.bakery.services.ContactSuccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactSuccessController {

    private final ContactSuccessService contactSuccessService;

    @Autowired
    public ContactSuccessController(ContactSuccessService contactSuccessService) {
        this.contactSuccessService = contactSuccessService;
    }

    @GetMapping("/success")
    public ResponseEntity<ContactSuccessDto> getContactSuccessInfo() {
        return ResponseEntity.ok(contactSuccessService.getContactSuccessInfo());
    }
}