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
    public ProjectEntity saveProject(ProjectEntity projectEntity) {
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

    @Override
    public boolean isExist(Long id) {
        return projectRepository.existsById(id);
    }

    @Override
    public ProjectEntity partialUpdate(Long id, ProjectEntity projectEntity) {
        projectEntity.setId(id);

        return projectRepository.findById(id).map(existingProject -> {
            Optional.ofNullable(projectEntity.getName()).ifPresent(existingProject::setName);
            return projectRepository.save(existingProject);
        }).orElseThrow(() -> new RuntimeException("Project does not exist!"));
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}
