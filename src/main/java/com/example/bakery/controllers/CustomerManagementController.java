package com.example.bakery.controllers;

import com.example.bakery.dto.CustomerDto;
import com.example.bakery.dto.CustomerSearchCriteria;
import com.example.bakery.services.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerManagementController {

    private final CustomerManagementService customerManagementService;

    @Autowired
    public CustomerManagementController(CustomerManagementService customerManagementService) {
        this.customerManagementService = customerManagementService;
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDto>> searchCustomers(
            @RequestParam(required = false) String searchTerm,
            Pageable pageable) {
        CustomerSearchCriteria criteria = new CustomerSearchCriteria();
        criteria.setSearchTerm(searchTerm);
        Page<CustomerDto> customers = customerManagementService.searchCustomers(criteria, pageable);
        return ResponseEntity.ok(customers);
    }
}