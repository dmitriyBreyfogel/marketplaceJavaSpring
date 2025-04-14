package com.example.marketplace.controllers;

import com.example.marketplace.models.Product;
import com.example.marketplace.models.ProductType;
import com.example.marketplace.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {
    private final ProductRepository productRepository;

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/search")
    public String searchPage(Model model) {
        model.addAttribute("title", "Поиск");
        return "search";
    }

    @GetMapping("/category/{type}")
    public String electronicPage(@PathVariable String type, Model model) {
        ProductType productType = ProductType.fromString(type.toUpperCase());
        List<Product> products = productRepository.findByType(productType);
        model.addAttribute("products", products);
        model.addAttribute("productType", productType);
        model.addAttribute("title", productType.getDisplayName());
        return "productsByType";
    }
}
