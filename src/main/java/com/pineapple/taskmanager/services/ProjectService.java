package com.pineapple.taskmanager.services;


import com.pineapple.taskmanager.domain.entities.ProjectEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    ProjectEntity saveProject(ProjectEntity projectEntity);

    List<ProjectEntity> findAll();

    Optional<ProjectEntity> findOne(Long id);

    boolean isExist(Long id);
}
