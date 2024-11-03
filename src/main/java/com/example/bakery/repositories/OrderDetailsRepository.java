package com.example.bakery.repositories;

import com.example.bakery.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    void deleteByOrderId(Integer orderId);
}
