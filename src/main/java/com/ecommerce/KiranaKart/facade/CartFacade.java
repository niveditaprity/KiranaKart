package com.ecommerce.KiranaKart.facade;

import com.ecommerce.KiranaKart.entity.CartItem;
import com.ecommerce.KiranaKart.service.CartService;
import com.ecommerce.KiranaKart.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartFacade {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CartService cartService;
    public void addItemToCart(String token, Long productId, Integer quantity) {
        String username = jwtUtil.getUserIdFromToken(token);
        cartService.addItemToCart(username,productId,quantity);

    }

    public List<CartItem> cartItemList(String token) {
        String username = jwtUtil.getUserIdFromToken(token);
        return cartService.cartItemList(username);

    }

    public List<CartItem> updateCartItemList(String token,Long productId) {
       return  cartService.updateCartItemList(token,productId);
    }


}
