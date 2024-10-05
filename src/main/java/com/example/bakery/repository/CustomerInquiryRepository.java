package com.example.bakery.repository;

import com.example.bakery.models.CustomerInquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerInquiryRepository extends JpaRepository<CustomerInquiry, Long> {
}