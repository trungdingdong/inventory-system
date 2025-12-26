package com.example.inventory_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.inventory_system.entity.User;
import com.example.inventory_system.service.UserService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {

        User savedUser = userService.register(user.getUsername(), user.getPassword());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successful");
        response.put("username", savedUser.getUsername());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){

        User loggedinUser = userService.login(user.getUsername(), user.getPassword());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successful");
        response.put("data", loggedinUser.getUsername());
        return ResponseEntity.ok(response);
    }
}
