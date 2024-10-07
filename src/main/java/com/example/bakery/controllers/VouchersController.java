package com.example.bakery.controllers;

import com.example.bakery.models.Vouchers;
import com.example.bakery.services.VouchersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vouchers")
public class VouchersController {

    private final VouchersService vouchersService;

    @Autowired
    public VouchersController(VouchersService vouchersService) {
        this.vouchersService = vouchersService;
    }

    @GetMapping
    public ResponseEntity<List<Vouchers>> getAllVouchers() {
        List<Vouchers> vouchers = vouchersService.getAllVouchers();
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vouchers> getVoucherById(@PathVariable Long id) {
        return vouchersService.getVoucherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vouchers> createVoucher(@RequestBody Vouchers voucher) {
        Vouchers createdVoucher = vouchersService.createVoucher(voucher);
        return ResponseEntity.ok(createdVoucher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vouchers> updateVoucher(@PathVariable Long id, @RequestBody Vouchers voucher) {
        return vouchersService.updateVoucher(id, voucher)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoucher(@PathVariable Long id) {
        boolean deleted = vouchersService.deleteVoucher(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
