package org.example.bloodbridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    // Serve the index page
    @GetMapping
    public String homepage() {
        return "redirect:/index.html"; // Redirect to the index.html in static folder
    }

    // Serve the admin login page
    @GetMapping("/admin/login")
    public String adminLogin() {
        return "redirect:/admin-login.html"; // Redirect to the admin-login.html in static folder
    }

    // Serve the registration page
    @GetMapping("/register")
    public String registrationForm() {
        return "redirect:/registration.html"; // Redirect to the registration.html in static folder
    }
}
