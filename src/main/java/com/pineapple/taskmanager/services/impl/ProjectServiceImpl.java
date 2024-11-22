package com.pineapple.taskmanager.services.impl;

import com.pineapple.taskmanager.domain.entities.ProjectEntity;
import com.pineapple.taskmanager.repositories.ProjectRepository;
import com.pineapple.taskmanager.services.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectEntity createProject(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }
}
