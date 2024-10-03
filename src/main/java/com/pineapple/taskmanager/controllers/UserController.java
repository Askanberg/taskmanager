/*package com.pineapple.taskmanager.controllers;

import com.pineapple.taskmanager.domain.User;
import com.pineapple.taskmanager.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    List<User> allUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    User oneUser(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> UserNotFoundException(userId));
    }


    @DeleteMapping
    void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    //********************** Help methods *************************
    private RuntimeException UserNotFoundException(Long userId) {
        return new RuntimeException("User with id " + userId + " could not be found.");
    }

}
*/
