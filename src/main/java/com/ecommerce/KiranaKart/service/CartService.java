package com.ecommerce.KiranaKart.service;

import com.ecommerce.KiranaKart.dto.CartDto;
import com.ecommerce.KiranaKart.entity.Cart;
import com.ecommerce.KiranaKart.entity.CartItem;
import com.ecommerce.KiranaKart.entity.Product;
import com.ecommerce.KiranaKart.entity.User;
import com.ecommerce.KiranaKart.repository.CartItemRepository;
import com.ecommerce.KiranaKart.repository.CartRepository;
import com.ecommerce.KiranaKart.repository.ProductRepository;
import com.ecommerce.KiranaKart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private  UserService userService;
    public void addItemToCart(String username, Long productId, Integer quantity) {
        // Fetch the user, handling the case where the user might not be found
        User user = userRepository.findByUsername(username);

        // Fetch the user's cart or create a new one if it doesn't exist
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user); // Ensure the cart is associated with the user
            user.setCart(cart);
            cart.setCartItems(new ArrayList<>());
            cartRepository.save(cart);
        }

        // Fetch the product, handling the case where the product might not be found
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the cart already contains an item for this product
        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            // Update quantity if the item already exists in the cart
            cart.addItemToCart(existingCartItem,quantity);
            cartItemRepository.save(existingCartItem);
        } else {
            // Create a new CartItem if it does not exist in the cart
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
            cart.getCartItems().add(cartItem); // Add the new item to the cart
            cart.addItemToCart(cartItem,quantity);
        }

        // Save the updated cart
        cartRepository.save(cart);
    }

    public List<CartItem> cartItemList(String username) {
        User user = userRepository.findByUsername(username);
        return user.getCart().getCartItems();
    }

    public List<CartItem> updateCartItemList(String token, Long productId) {
        User user = userService.getUserByToken(token);
        Cart cart = user.getCart();
        cart.removeItemByProductId(productId);
        cartRepository.save(cart);
        List<CartItem>cartItemList = cart.getCartItems();
        return cartItemList;

    }

    public CartDto cartData(String token) {
        User user = userService.getUserByToken(token);
        Cart cart = user.getCart();
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setCartItems(cart.getCartItems());
        cartDto.setTotalCost(cart.getTotalCost());
        return cartDto;

    }

    public void deleteCart(String token) {
        User user = userService.getUserByToken(token);
        Cart cart = user.getCart();
        if (cart != null) {// Deletes the cart from the repository
            user.setCart(null); // Set the user's cart reference to null
            userRepository.save(user); // Save the updated user before deleting the cart

            cartRepository.delete(cart); // Now delete the cart from the repository

        }
    }
}
