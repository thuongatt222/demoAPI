package com.example.demoapi.controller;

import com.example.demoapi.DTO.request.UserRequest;
import com.example.demoapi.DTO.response.UserResponse;
import com.example.demoapi.domain.User;
import com.example.demoapi.exception.ResourceNotFoundException;
import com.example.demoapi.service.UserSevice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    // Get all user
    @Autowired
    private UserSevice userSevice;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        List<User> users = userSevice.findAll();
        return users.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = userSevice.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return convertToResponse(user);
    }

    @PostMapping
    public UserResponse addUser(@RequestBody UserRequest userRequest) {
        return userSevice.addUser(userRequest);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return userSevice.updateUser(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userSevice.deleteUser(id);
    }
    private UserResponse convertToResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
