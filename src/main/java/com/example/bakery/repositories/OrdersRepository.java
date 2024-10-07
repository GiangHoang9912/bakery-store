package com.example.bakery.repositories;

import com.example.bakery.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    // Find orders by user ID
    List<Orders> findByUserId(Long userId);

    // Find orders created after a certain date
    List<Orders> findByCreatedAtAfter(LocalDateTime date);

    // Find orders by user ID and created after a certain date
    List<Orders> findByUserIdAndCreatedAtAfter(Long userId, LocalDateTime date);

    // You can add more custom query methods as needed
}
