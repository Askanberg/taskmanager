package com.pineapple.taskmanager.services;


import com.pineapple.taskmanager.domain.entities.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    ProjectEntity saveProject(ProjectEntity projectEntity);

    List<ProjectEntity> findAll();

    Page<ProjectEntity> findAll(Pageable pageable);

    Optional<ProjectEntity> findOne(Long id);

    boolean isExist(Long id);

    ProjectEntity partialUpdate(Long id, ProjectEntity projectEntity);

    void delete(Long id);
}
