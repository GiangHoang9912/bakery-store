package com.example.bakery.services;

import com.example.bakery.dto.CustomerDto;
import com.example.bakery.dto.CustomerSearchCriteria;
import com.example.bakery.models.Customer;
import com.example.bakery.repository.CustomerRepository;

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
public class CustomerManagementService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerManagementService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<CustomerDto> searchCustomers(CustomerSearchCriteria criteria, Pageable pageable) {
        Specification<Customer> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getSearchTerm() != null && !((Streamable<CustomerDto>) criteria.getSearchTerm()).isEmpty()) {
                String searchTerm = "%" + ((String) criteria.getSearchTerm()).toLowerCase() + "%";
                predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), searchTerm),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), searchTerm),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("phoneNumber")), searchTerm)
                ));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return customerRepository.findAll(spec, pageable).map(this::convertToDto);
    }

    private CustomerDto convertToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        return dto;
    }
}