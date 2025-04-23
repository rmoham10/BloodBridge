package org.example.bloodbridge.controller;

import org.example.bloodbridge.entity.User;
import org.example.bloodbridge.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/dummy")
    public ResponseEntity<String> dummy(){
        return ResponseEntity.ok("dummy successful!  admin approval.");
    }

    // User registration endpoint
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
        @RequestParam("name") String name,
        @RequestParam("email") String email,
        @RequestParam("phone") String phone,
        @RequestParam("bloodGroup") String bloodGroup,
        @RequestParam("location") String location,
        @RequestParam("bloodDocument") MultipartFile bloodDocument
    ) {
        try {
            userService.registerUser(name, email, phone, bloodGroup, location, bloodDocument);
            return ResponseEntity.ok("Registration successful! Pending admin approval.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
