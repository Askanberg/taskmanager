package com.pineapple.taskmanager.services.impl;

import com.pineapple.taskmanager.domain.entities.UserEntity;
import com.pineapple.taskmanager.repositories.UserRepository;
import com.pineapple.taskmanager.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

}
