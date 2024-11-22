package com.pineapple.taskmanager.services;

import com.pineapple.taskmanager.domain.entities.TaskEntity;

public interface TaskService {

    TaskEntity createTask(TaskEntity taskEntity);
}
