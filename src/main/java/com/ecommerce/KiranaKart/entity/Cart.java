package com.ecommerce.KiranaKart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Iterator;
import java.util.List;

@Entity
@Data
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    private Double totalCost = 0.0;

    public void addItemToCart(CartItem cartItem,Integer quantity) {
        if(cartItems.contains(cartItem)) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }else {
            cartItems.add(cartItem);
        }
        recalculateTotalCost();
    }

    public void removeItemFromCart(CartItem cartItem) {
        if(cartItems.contains(cartItem)) {
            cartItems.remove(cartItem);
        }
        recalculateTotalCost();
    }

    public void removeItemByProductId(Long productId) {
        Iterator<CartItem> iterator = cartItems.iterator();

        while (iterator.hasNext()) {
            CartItem cartItem = iterator.next();
            if (cartItem.getProduct().getId().equals(productId)) {
                if (cartItem.getQuantity() > 1) {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                } else {
                    iterator.remove(); // Remove the item from the list
                }
                recalculateTotalCost();
                return; // Exit after finding and processing the item
            }
        }
    }
    private void recalculateTotalCost() {
        totalCost = 0.0;
        for (CartItem cartItem : cartItems) {
            totalCost += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
    }
}
