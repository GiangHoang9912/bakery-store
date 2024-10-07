package com.example.bakery.services;

import com.example.bakery.models.Vouchers;
import com.example.bakery.repositories.VouchersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VouchersService {

    private final VouchersRepository vouchersRepository;

    @Autowired
    public VouchersService(VouchersRepository vouchersRepository) {
        this.vouchersRepository = vouchersRepository;
    }

    public List<Vouchers> getAllVouchers() {
        return vouchersRepository.findAll();
    }

    public Optional<Vouchers> getVoucherById(Long id) {
        return vouchersRepository.findById(id);
    }

    public Vouchers createVoucher(Vouchers voucher) {
        voucher.setCreatedAt(LocalDateTime.now());
        voucher.setUpdatedAt(LocalDateTime.now());
        return vouchersRepository.save(voucher);
    }

    public Optional<Vouchers> updateVoucher(Long id, Vouchers voucherDetails) {
        return vouchersRepository.findById(id)
                .map(existingVoucher -> {
                    existingVoucher.setCode(voucherDetails.getCode());
                    existingVoucher.setDiscount(voucherDetails.getDiscount());
                    existingVoucher.setProduct(voucherDetails.getProduct());
                    existingVoucher.setExpiredAt(voucherDetails.getExpiredAt());
                    existingVoucher.setUpdatedAt(LocalDateTime.now());
                    return vouchersRepository.save(existingVoucher);
                });
    }

    public boolean deleteVoucher(Long id) {
        return vouchersRepository.findById(id)
                .map(voucher -> {
                    vouchersRepository.delete(voucher);
                    return true;
                })
                .orElse(false);
    }
}
