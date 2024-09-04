package com.ecommerce.KiranaKart.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class ProductDto {
    private String name; // Name of the product
    private String description; // Description of the product
    private Double price; // Price of the product
    private int quantity; // Quantity in stock
    private long category_id; // Category of the product
    private String brand; // Brand of the product
    private String sku; // Stock Keeping Unit
    private LocalDate dateAdded; // Date when the product was added
    private String imageUrl; // URL for the product image
    private String status;
}
