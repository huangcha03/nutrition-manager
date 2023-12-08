package com.nutritiontracker.nutritiontrackerbackend.user.controller;

import com.nutritiontracker.nutritiontrackerbackend.user.model.User;
import com.nutritiontracker.nutritiontrackerbackend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// figure out how to use SpringSecurity with users and SQL/hibernate
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    UserService us = new UserService();

    @GetMapping("/user/{id}")
    public User viewLoginInfo(@PathVariable Long id) {
        return us.getAccount(id);
    }

    @PostMapping("/user/create")
    public User createNewAccount(@RequestBody User user) {
        return us.createNewAccount(user);
    }
}
