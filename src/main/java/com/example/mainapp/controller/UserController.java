package com.example.mainapp.controller;

import com.example.mainapp.entity.User;
import com.example.mainapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello")
    public String securityUrl() {
        return "hello";

    }

    @GetMapping("/products")
    public String mainPage() {
        return "products";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "User in email: "
                    + user.getEmail() + "Already exists");
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/products";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProductList());
        return "user.info";
    }
}