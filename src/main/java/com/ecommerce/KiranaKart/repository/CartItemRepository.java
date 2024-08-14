package com.ecommerce.KiranaKart.repository;

import com.ecommerce.KiranaKart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository  extends JpaRepository<CartItem, Long> {
}
