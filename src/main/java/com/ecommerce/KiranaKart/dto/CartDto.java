package com.ecommerce.KiranaKart.dto;

import com.ecommerce.KiranaKart.entity.CartItem;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private Long id;
    private List<CartItem> cartItems;
    private Double totalCost;
}
