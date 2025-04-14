package com.example.marketplace.repositories;

import com.example.marketplace.models.Product;
import com.example.marketplace.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByType(ProductType type);
}
