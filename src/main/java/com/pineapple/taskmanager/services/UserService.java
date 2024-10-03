package com.pineapple.taskmanager.services;

import com.pineapple.taskmanager.domain.Project;
import com.pineapple.taskmanager.domain.User;
import com.pineapple.taskmanager.repositories.ProjectRepository;
import com.pineapple.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/*
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}

 */
