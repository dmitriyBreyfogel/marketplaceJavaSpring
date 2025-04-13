package com.example.marketplace.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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

    @GetMapping("/electronic")
    public String electronicPage(Model model) {
        model.addAttribute("title", "Электроника");
        return "electronic";
    }

    @GetMapping("/clothes")
    public String clothesPage(Model model) {
        model.addAttribute("title", "Одежда");
        return "clothes";
    }

    @GetMapping("/books")
    public String booksPage(Model model) {
        model.addAttribute("title", "Книги");
        return "books";
    }

    @GetMapping("/house")
    public String housePage(Model model) {
        model.addAttribute("title", "Для дома");
        return "house";
    }
}
