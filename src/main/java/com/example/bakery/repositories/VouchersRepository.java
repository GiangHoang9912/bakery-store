package com.example.bakery.repositories;

import com.example.bakery.models.Vouchers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VouchersRepository extends JpaRepository<Vouchers, Integer> {
    List<Vouchers> findByExpiredAtAfter(LocalDateTime date);
    List<Vouchers> findByCode(String code);
    List<Vouchers> findByProductId(Integer productId);
}
