package com.example.marketplace.controllers;

import com.example.marketplace.models.Product;
import com.example.marketplace.models.ProductType;
import com.example.marketplace.models.User;
import com.example.marketplace.repositories.ProductRepository;
import com.example.marketplace.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {
    private final ProductService productService;

    // Конструктор вместо @RequiredArgsConstructor
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("title", "Главная страница");
        model.addAttribute("products", productService.listProducts());
        return "home";
    }

    @GetMapping("/category/{productType}")
    public String getProductsByType(@PathVariable ProductType productType, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("title", productType.getDisplayName());
        model.addAttribute("productType", productType);
        model.addAttribute("products", productService.getProductsByType(productType));
        return "productsByType";
    }
}
