package com.example.shop.controllers;

import com.example.shop.models.CartItem;
import com.example.shop.models.User;
import com.example.shop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{productId}")
    public ResponseEntity<CartItem> addToCart(@AuthenticationPrincipal User user,
                                              @PathVariable Long productId,
                                              @RequestParam int quantity) {
        CartItem cartItem = cartService.addToCart(user, productId, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems(@AuthenticationPrincipal User user) {
        List<CartItem> cartItems = cartService.getCartItems(user);
        return ResponseEntity.ok(cartItems);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromCart(@AuthenticationPrincipal User user,
                                               @PathVariable Long productId) {
        cartService.removeFromCart(user, productId);
        return ResponseEntity.noContent().build();
    }
}
