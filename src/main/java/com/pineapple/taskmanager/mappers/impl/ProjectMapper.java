package com.pineapple.taskmanager.mappers.impl;

import com.pineapple.taskmanager.domain.dto.ProjectDto;
import com.pineapple.taskmanager.domain.entities.ProjectEntity;
import com.pineapple.taskmanager.domain.entities.TaskEntity;
import com.pineapple.taskmanager.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper implements Mapper<ProjectEntity, ProjectDto> {

    private ModelMapper modelMapper;

    public ProjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProjectDto mapTo(ProjectEntity projectEntity) {
        return modelMapper.map(projectEntity, ProjectDto.class);
    }

    @Override
    public ProjectEntity mapFrom(ProjectDto projectDto) {
        return modelMapper.map(projectDto, ProjectEntity.class);
    }
}
