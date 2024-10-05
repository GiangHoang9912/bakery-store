package com.example.bakery.repository;

import com.example.bakery.models.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<ContactInfo, Long> {
    Optional<ContactInfo> findFirstByOrderByIdAsc();
}