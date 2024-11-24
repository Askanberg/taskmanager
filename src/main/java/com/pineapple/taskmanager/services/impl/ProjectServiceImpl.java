package com.pineapple.taskmanager.services.impl;

import com.pineapple.taskmanager.domain.entities.ProjectEntity;
import com.pineapple.taskmanager.repositories.ProjectRepository;
import com.pineapple.taskmanager.services.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<ProjectEntity> findAll() {
        return StreamSupport.stream(projectRepository.
                findAll().
                spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectEntity> findOne(Long id){
        return projectRepository.findById(id);
    }
}
