package com.example.bakery.repositories;

import com.example.bakery.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
    // You can add custom query methods here if needed
}
