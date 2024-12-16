package com.example.shop.services;

import com.example.shop.models.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User findByUsername(String username); // Добавляем метод для поиска пользователя по имени
}
