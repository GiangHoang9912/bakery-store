package com.example.bakery.controllers;

import com.example.bakery.dto.ProductDto;
import com.example.bakery.dto.ProductSearchCriteria;
import com.example.bakery.services.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductManagementController {

    private final ProductManagementService productManagementService;

    @Autowired
    public ProductManagementController(ProductManagementService productManagementService) {
        this.productManagementService = productManagementService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> searchProducts(
            @RequestParam(required = false) String searchTerm,
            Pageable pageable) {
        ProductSearchCriteria criteria = new ProductSearchCriteria();
        criteria.setSearchTerm(searchTerm);
        Page<ProductDto> products = productManagementService.searchProducts(criteria, pageable);
        return ResponseEntity.ok(products);
    }
}
