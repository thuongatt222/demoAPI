package com.example.demoapi.service;

import com.example.demoapi.DTO.request.UserRequest;
import com.example.demoapi.DTO.response.UserResponse;
import com.example.demoapi.domain.User;
import com.example.demoapi.exception.ResourceNotFoundException;
import com.example.demoapi.repositores.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSevice {
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public UserResponse addUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword()); // Mã hóa mật khẩu
        user = userRepository.save(user);
        return convertToResponse(user);
    }

    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword()); // Mã hóa mật khẩu
        user = userRepository.save(user);
        return convertToResponse(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    private UserResponse convertToResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
