package com.example.bakery.services;

import com.example.bakery.dto.ContactSuccessDto;
import org.springframework.stereotype.Service;

@Service
public class ContactSuccessService {

    public ContactSuccessDto getContactSuccessInfo() {
        ContactSuccessDto dto = new ContactSuccessDto();
        dto.setMessage("Thank you for contacting us!");
        dto.setSubMessage("We have received your inquiry and will respond as soon as possible.");
        dto.setContactEmail("support@bakery.com");
        dto.setContactPhone("+1 234 567 8900");
        return dto;
    }
}