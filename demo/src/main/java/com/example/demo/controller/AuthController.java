package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role,
                               Model model) {
        boolean isRegistered = userService.registerUser(email, password, role);

        if (isRegistered) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Invalid email format. Please try again.");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model) {
        User user = userService.loginUser(email, password);

        if (user != null) {
            model.addAttribute("message", "Welcome, " + user.getRole());
            return "welcome";
        } else {
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "login";
        }
    }
}
