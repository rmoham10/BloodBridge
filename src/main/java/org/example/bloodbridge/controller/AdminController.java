package org.example.bloodbridge.controller;

import org.example.bloodbridge.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

   @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Admin login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        boolean isAuthenticated = adminService.authenticate(username, password);

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


    @PostMapping("/request-blood")
    public ResponseEntity<String> requestBlood(@RequestParam Long bloodGroupId, @RequestParam String clinicDetails) {
        try {
            adminService.requestBlood(bloodGroupId, clinicDetails);
            return ResponseEntity.ok("Blood request successfully sent to eligible donors!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
