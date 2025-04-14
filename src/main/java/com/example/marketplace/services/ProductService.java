package com.example.marketplace.services;

import com.example.marketplace.models.Product;
import com.example.marketplace.models.ProductType;
import com.example.marketplace.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Получить все товары (без фильтрации)
     */
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Получить товары с пагинацией
     */
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Получить товары по типу
     */
    public List<Product> findByType(ProductType type) {
        return productRepository.findByType(type);
    }

    /**
     * Поиск товаров по названию (без учета регистра)
     */
    public List<Product> searchByName(String query) {
        return productRepository.findByNameContainingIgnoreCase(query);
    }

    /**
     * Получить товары в ценовом диапазоне

    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByP(minPrice, maxPrice);
    }
     */

    /**
     * Получить N самых дорогих товаров
     */
    public List<Product> findTopExpensive(int limit) {
        return productRepository.findTopExpensiveProducts(limit);
    }

    /**
     * Получить все доступные типы товаров
     */
    public List<ProductType> findAllProductTypes() {
        return productRepository.findAllDistinctTypes();
    }

    /**
     * Создать новый товар (только для админа)
     */
    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Обновить существующий товар (только для админа)
     */
    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setType(updatedProduct.getType());
        product.setImagePath(updatedProduct.getImagePath());

        return productRepository.save(product);
    }

    /**
     * Удалить товар (только для админа)
     */
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}