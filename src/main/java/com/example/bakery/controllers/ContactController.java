package com.example.bakery.controllers;

import com.example.bakery.dto.ContactInfoDto;
import com.example.bakery.dto.CustomerInquiryDto;
import com.example.bakery.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/info")
    public ResponseEntity<ContactInfoDto> getContactInfo() {
        return ResponseEntity.ok(contactService.getContactInfo());
    }

    @PostMapping("/inquiry")
    public ResponseEntity<String> submitInquiry(@RequestBody CustomerInquiryDto inquiryDto) {
        contactService.submitInquiry(inquiryDto);
        return ResponseEntity.ok("Inquiry submitted successfully");
    }
}