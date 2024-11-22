package com.pineapple.taskmanager.services;


import com.pineapple.taskmanager.domain.entities.ProjectEntity;

public interface ProjectService {
    ProjectEntity createProject(ProjectEntity projectEntity);
}
