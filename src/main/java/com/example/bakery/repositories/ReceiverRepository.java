package com.example.bakery.repositories;

import com.example.bakery.models.Receivers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiverRepository extends JpaRepository<Receivers, Long> {
    // You can add custom query methods here if needed
}
