package com.example.marketplace.services;

import org.springframework.stereotype.Service;
import com.example.marketplace.models.Product;
import com.example.marketplace.models.ProductType;
import com.example.marketplace.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Получение всех продуктов
     * @return список всех продуктов
     */
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    /**
     * Получение продукта по ID
     * @param id идентификатор продукта
     * @return продукт или null если не найден
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Сохранение или обновление продукта
     * @param product продукт для сохранения
     * @return сохраненный продукт
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Удаление продукта по ID
     * @param id идентификатор продукта
     */
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Получение продуктов по типу
     * @param productType тип продукта
     * @return список продуктов указанного типа
     */
    public List<Product> getProductsByType(ProductType productType) {
        return productRepository.findByProductType(productType);
    }

    /**
     * Поиск продуктов по названию (содержит текст)
     * @param name текст для поиска в названии
     * @return список найденных продуктов
     */
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}