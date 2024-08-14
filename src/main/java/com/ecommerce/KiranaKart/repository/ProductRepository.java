package com.ecommerce.KiranaKart.repository;

import com.ecommerce.KiranaKart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(String sku);
}
