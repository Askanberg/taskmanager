package com.pineapple.taskmanager.controllers;

import com.pineapple.taskmanager.domain.dto.UserDto;
import com.pineapple.taskmanager.domain.entities.UserEntity;
import com.pineapple.taskmanager.mappers.Mapper;
import com.pineapple.taskmanager.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService userService;
    private Mapper<UserEntity, UserDto> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserEntity userEntity = userMapper.mapFrom(user);
        UserEntity savedUserEntity = userService.createUser(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(savedUserEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/users")
    public List<UserDto> listUsers() {
        List<UserEntity> users = userService.findAll();
        return users.stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());

    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        Optional<UserEntity> foundUser = userService.findOne(id);
        return foundUser.map(userEntity -> {
            UserDto userDto = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}

