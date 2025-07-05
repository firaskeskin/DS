package com.example.user_service.controller;

import com.example.user_service.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<User> users = Arrays.asList(
            new User("1", "Alice", "student"),
            new User("2", "Bob", "trainer"),
            new User("3", "Charlie", "admin")
    );

    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
