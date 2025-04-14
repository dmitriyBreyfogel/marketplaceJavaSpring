package com.example.marketplace.controllers;

import com.example.marketplace.models.ProductType;
import com.example.marketplace.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    ProductService productService;

    @GetMapping("/")
    public String homePage(Model model) {
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
        model.addAttribute("title", productType.getDisplayName());
        model.addAttribute("products", productService.findByType(productType));

        return "products";
    }


}
