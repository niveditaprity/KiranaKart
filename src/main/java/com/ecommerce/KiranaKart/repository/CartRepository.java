package com.ecommerce.KiranaKart.repository;

import com.ecommerce.KiranaKart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
