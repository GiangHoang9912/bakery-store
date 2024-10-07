package com.example.bakery.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SystemConfigs")
public class SystemConfigs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String key;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Constructors, getters, and setters
}
