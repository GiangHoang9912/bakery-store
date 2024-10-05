package com.example.bakery.controllers;

import com.example.bakery.dto.CreateProductDto;
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

    // ... existing methods ...

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        ProductDto createdProduct = productManagementService.createProduct(createProductDto);
        return ResponseEntity.ok(createdProduct);
    }
}