package com.ecommerce.KiranaKart.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Long id;
    private long productId; // Unique identifier for the product
    private String name; // Name of the product
    private String description; // Description of the product
    private Double price; // Price of the product
    private int quantity; // Quantity in stock
    private long category_id; // Category of the product
    private String brand; // Brand of the product
    private String sku; // Stock Keeping Unit
    private LocalDate dateAdded; // Date when the product was added
    private String imageUrl; // URL for the product image
    private String status; // Status of the product (e.g., active, discontinued)
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
