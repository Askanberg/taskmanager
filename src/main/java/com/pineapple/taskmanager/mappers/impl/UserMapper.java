package com.pineapple.taskmanager.mappers.impl;

import com.pineapple.taskmanager.domain.dto.UserDto;
import com.pineapple.taskmanager.domain.entities.TaskEntity;
import com.pineapple.taskmanager.domain.entities.UserEntity;
import com.pineapple.taskmanager.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserEntity, UserDto> {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
