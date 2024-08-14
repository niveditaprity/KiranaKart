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

    private Double totalCost;

    public void addItemToCart(CartItem cartItem) {
        if(cartItems.contains(cartItem)) {
            cartItem.setQuantity(cartItem.getQuantity()+1);
        }else {
            cartItems.add(cartItem);
        }
    }

    public void removeItemFromCart(CartItem cartItem) {
        if(cartItems.contains(cartItem)) {
            cartItems.remove(cartItem);
        }
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
                return; // Exit after finding and processing the item
            }
        }
    }
}
