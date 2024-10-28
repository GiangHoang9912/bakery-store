package com.example.bakery.controllers;

import com.example.bakery.models.ContactUs;
import com.example.bakery.services.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-us")
public class ContactUsController {

    private final ContactUsService contactUsService;

    @Autowired
    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @GetMapping
    public ResponseEntity<List<ContactUs>> getAllContactUs() {
        List<ContactUs> contactUsList = contactUsService.getAllContactUs();
        return ResponseEntity.ok(contactUsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactUs> getContactUsById(@PathVariable String id) {
        return contactUsService.getContactUsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContactUs> createContactUs(@RequestBody ContactUs contactUs) {
        ContactUs createdContactUs = contactUsService.createContactUs(contactUs);
        return ResponseEntity.ok(createdContactUs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactUs> updateContactUs(@PathVariable String id, @RequestBody ContactUs contactUs) {
        ContactUs updatedContactUs = contactUsService.updateContactUs(id, contactUs);
        return ResponseEntity.ok(updatedContactUs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactUs(@PathVariable String id) {
        contactUsService.deleteContactUs(id);
        return ResponseEntity.noContent().build();
    }
}
