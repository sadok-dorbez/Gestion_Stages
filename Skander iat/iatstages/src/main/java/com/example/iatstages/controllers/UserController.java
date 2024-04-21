package com.example.iatstages.controllers;

import com.example.iatstages.entities.User;
import com.example.iatstages.services.UserService.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private IUserService userService;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/activate/{id}")
    public String activateUser(@PathVariable Long id) {
        return userService.activate(id);
    }
}
