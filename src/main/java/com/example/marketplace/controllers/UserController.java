package com.example.marketplace.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.marketplace.models.User;
import com.example.marketplace.services.UserService;

@Controller
public class UserController {
    private final UserService userService;

    // Конструктор вместо @RequiredArgsConstructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String startPage() {
        return "start";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        if (userService.authenticate(email, password)) {
            User user = userService.findByEmail(email);
            session.setAttribute("user", user);
            // После успешного входа перенаправляем на главную страницу с товарами
            return "redirect:/home";
        } else {
            model.addAttribute("loginError", "Неверный email или пароль");
            return "auth";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        if (userService.saveUser(user)) {
            // После успешной регистрации показываем форму входа
            return "redirect:/?registered=true";
        } else {
            model.addAttribute("registerError", "Пользователь с таким email или именем уже существует");
            return "auth";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}