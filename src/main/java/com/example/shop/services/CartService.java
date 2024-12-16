package com.example.shop.services;

import com.example.shop.models.CartItem;
import com.example.shop.models.Product;
import com.example.shop.models.User;
import com.example.shop.repositories.CartItemRepository;
import com.example.shop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository; // Зависимость для Product

    // Добавить товар в корзину
    public CartItem addToCart(User user, Long productId, int quantity) {
        // Получаем продукт из базы данных по ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Создаем объект CartItem с продуктом и пользователем
        CartItem cartItem = new CartItem(user, product, quantity);

        // Сохраняем CartItem в репозитории
        return cartItemRepository.save(cartItem);
    }

    // Получить все товары в корзине пользователя
    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    // Удалить товар из корзины
    public void removeFromCart(User user, Long productId) {
        // Сначала получаем продукт по ID
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Затем ищем CartItem по пользователю и продукту
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        cartItemRepository.delete(cartItem);
    }

}
