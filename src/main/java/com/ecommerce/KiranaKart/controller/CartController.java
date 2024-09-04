package com.ecommerce.KiranaKart.controller;

import com.ecommerce.KiranaKart.dto.CartDto;
import com.ecommerce.KiranaKart.entity.CartItem;
import com.ecommerce.KiranaKart.facade.CartFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart/")
public class CartController {
    @Autowired
    private CartFacade cartFacade;
    @PostMapping("add/item")
    ResponseEntity<String>addItemToCart(@RequestHeader("Authorization") String authHeader,
                                        @RequestParam Long productId,
                                        @RequestParam Integer quantity) {

        String token = authHeader.replace("Bearer ", "");
        cartFacade.addItemToCart(token, productId, quantity);

        return ResponseEntity.ok("Item added to cart");
    }
    @GetMapping("item/list")
    ResponseEntity<List<CartItem>>cartItemList(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        List<CartItem> cartItemList = cartFacade.cartItemList(token);
        return ResponseEntity.ok(cartItemList);
    }

    @PutMapping("/update")
    ResponseEntity<List<CartItem>>updateCartItemList(@RequestHeader("Authorization") String authHeader,
    @RequestParam Long productId) {
        String token = authHeader.replace("Bearer ", "");
        List<CartItem> cartItemList = cartFacade.updateCartItemList(token,productId);
        return ResponseEntity.ok(cartItemList);
    }

    @GetMapping("/data")
    ResponseEntity<CartDto>cartData(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        CartDto  cartDto = cartFacade.cartData(token);
        return ResponseEntity.ok(cartDto);

    }
    @DeleteMapping("/delete")
    ResponseEntity<String>deleteCart(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        cartFacade.deleteCart(token);
        return ResponseEntity.ok("Cart is deleted successfully");

    }



}
