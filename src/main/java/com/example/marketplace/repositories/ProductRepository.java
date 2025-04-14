package com.example.marketplace.repositories;

import com.example.marketplace.models.Product;
import com.example.marketplace.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 1. Стандартные методы (уже есть в JpaRepository)
    // findAll(), findById(), save(), deleteById() и т.д. доступны автоматически

    // 2. Фильтрация по типу товара
    List<Product> findByType(ProductType type);

    // 3. Поиск по названию (без учета регистра)
    List<Product> findByNameContainingIgnoreCase(String name);

    // 4. Товары в ценовом диапазоне
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(@Param("minPrice") double minPrice,
                                   @Param("maxPrice") double maxPrice);

    // 5. Товары определенного типа с сортировкой по цене (возрастание)
    List<Product> findByTypeOrderByPriceAsc(ProductType type);

    // 6. Товары определенного типа с сортировкой по цене (убывание)
    List<Product> findByTypeOrderByPriceDesc(ProductType type);

    // 7. Поиск по части описания
    @Query("SELECT p FROM Product p WHERE LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Product> searchByDescription(@Param("query") String query);

    // 8. Получить все уникальные типы товаров
    @Query("SELECT DISTINCT p.type FROM Product p")
    List<ProductType> findAllDistinctTypes();

    // 9. Топ-N самых дорогих товаров
    @Query(value = "SELECT * FROM products ORDER BY price DESC LIMIT :limit",
            nativeQuery = true)
    List<Product> findTopExpensiveProducts(@Param("limit") int limit);

    // 10. Проверка существования товара с именем
    boolean existsByName(String name);
}
