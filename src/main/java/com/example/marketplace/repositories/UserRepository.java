package com.example.marketplace.repositories;

import com.example.marketplace.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Поиск пользователя по email
    Optional<User> findByEmail(String email);

    // Поиск пользователя по username
    Optional<User> findByUsername(String username);

    // Проверка существования пользователя по email
    boolean existsByEmail(String email);

    // Проверка существования пользователя по username
    boolean existsByUsername(String username);
}