package com.pineapple.taskmanager.controllers;

import com.pineapple.taskmanager.domain.dto.ProjectDto;
import com.pineapple.taskmanager.domain.entities.ProjectEntity;
import com.pineapple.taskmanager.mappers.Mapper;
import com.pineapple.taskmanager.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    private Mapper<ProjectEntity, ProjectDto> projectMapper;

    private ProjectService projectService;

    public ProjectController(Mapper<ProjectEntity, ProjectDto> projectMapper, ProjectService projectService) {
        this.projectMapper = projectMapper;
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectDto> createProject(
            @RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = projectMapper.mapFrom(projectDto);
        ProjectEntity savedProjectEntity = projectService.createProject(projectEntity);
        return new ResponseEntity<>(projectMapper.mapTo(savedProjectEntity), HttpStatus.CREATED);
    }
}
