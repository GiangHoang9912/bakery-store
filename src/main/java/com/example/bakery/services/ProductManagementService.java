package com.example.bakery.services;

import com.example.bakery.dto.ProductDto;
import com.example.bakery.dto.ProductSearchCriteria;
import com.example.bakery.models.Product;
import com.example.bakery.repository.ProductRepository;

import jakarta.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductManagementService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductDto> searchProducts(ProductSearchCriteria criteria, Pageable pageable) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getSearchTerm() != null && !((Streamable<ProductDto>) criteria.getSearchTerm()).isEmpty()) {
                String searchTerm = "%" + ((String) criteria.getSearchTerm()).toLowerCase() + "%";
                predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchTerm),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("productCode")), searchTerm)
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return productRepository.findAll(spec, pageable).map(this::convertToDto);
    }

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setProduct(product.getProduct());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        return dto;
    }
}