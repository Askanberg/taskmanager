package com.pineapple.taskmanager.services;

import com.pineapple.taskmanager.domain.entities.UserEntity;

public interface UserService {

    UserEntity createUser(UserEntity userEntity);
}

