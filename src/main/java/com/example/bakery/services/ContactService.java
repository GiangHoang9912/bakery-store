package com.example.bakery.services;

import com.example.bakery.dto.ContactInfoDto;
import com.example.bakery.dto.CustomerInquiryDto;
import com.example.bakery.models.ContactInfo;
import com.example.bakery.models.CustomerInquiry;
import com.example.bakery.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bakery.repository.CustomerInquiryRepository;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactInfoDto getContactInfo() {
        ContactInfo contactInfo = contactRepository.findFirstByOrderByIdAsc()
                .orElseThrow(() -> new RuntimeException("Contact information not found"));
        return convertToDto(contactInfo);
    }

    public void submitInquiry(CustomerInquiryDto inquiryDto) {
        CustomerInquiry inquiry = new CustomerInquiry();
        inquiry.setName(inquiryDto.getName());
        inquiry.setEmail(inquiryDto.getEmail());
        inquiry.setPhoneNumber(inquiryDto.getPhoneNumber());
        inquiry.setMessage(inquiryDto.getMessage());
        contactRepository.save(inquiry);
    }

    private ContactInfoDto convertToDto(ContactInfo contactInfo) {
        ContactInfoDto dto = new ContactInfoDto();
        dto.setAddress(contactInfo.getAddress());
        dto.setPhoneNumber(contactInfo.getPhoneNumber());
        dto.setEmail(contactInfo.getEmail());
        dto.setWorkingHours(contactInfo.getWorkingHours());
        return dto;
    }
}