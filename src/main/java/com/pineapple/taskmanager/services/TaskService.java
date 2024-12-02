package com.pineapple.taskmanager.services;

import com.pineapple.taskmanager.domain.entities.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskEntity saveTask(TaskEntity taskEntity);

    List<TaskEntity> findAll();

    Optional<TaskEntity> findOne(Long id);

    boolean isExists(Long id);
}
