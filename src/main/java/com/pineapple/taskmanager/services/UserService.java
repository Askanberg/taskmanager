package com.pineapple.taskmanager.services;

import com.pineapple.taskmanager.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity createUser(UserEntity userEntity);

    List<UserEntity> findAll();

    Optional<UserEntity> findOne(Long id);
}

