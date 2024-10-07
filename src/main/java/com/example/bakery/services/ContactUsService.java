package com.example.bakery.services;

import com.example.bakery.models.ContactUs;
import com.example.bakery.repositories.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactUsService {

    private final ContactUsRepository contactUsRepository;

    @Autowired
    public ContactUsService(ContactUsRepository contactUsRepository) {
        this.contactUsRepository = contactUsRepository;
    }

    public List<ContactUs> getAllContactUs() {
        return contactUsRepository.findAll();
    }

    public Optional<ContactUs> getContactUsById(String id) {
        return contactUsRepository.findById(id);
    }

    public ContactUs createContactUs(ContactUs contactUs) {
        return contactUsRepository.save(contactUs);
    }
}
