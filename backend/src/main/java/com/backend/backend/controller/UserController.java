package com.backend.backend.controller;

import com.backend.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/logIn")
    public ResponseEntity<String> logIn(
            @RequestParam String username,
            @RequestParam String password
    ) {
        return ResponseEntity.ok(userService.logIn(username, password));
    }
}

