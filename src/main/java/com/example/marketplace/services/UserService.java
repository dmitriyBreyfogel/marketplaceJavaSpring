package com.example.marketplace.services;

import org.springframework.stereotype.Service;
import com.example.marketplace.models.User;
import com.example.marketplace.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean saveUser(User user) {
        // Проверяем, существует ли пользователь с таким email или username
        if (userRepository.existsByEmail(user.getEmail())) {
            return false;
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }

        // Без шифрования пароля (не рекомендуется для реальных проектов)
        userRepository.save(user);
        return true;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean authenticate(String email, String password) {
        User user = findByEmail(email);
        if (user != null) {
            // Простая проверка пароля без шифрования
            return password.equals(user.getPassword());
        }
        return false;
    }
}