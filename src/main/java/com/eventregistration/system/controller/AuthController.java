package com.eventregistration.system.controller;

import com.eventregistration.system.entity.AppUser;
import com.eventregistration.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
     public ResponseEntity<?> registerUser(@RequestBody AppUser user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}



