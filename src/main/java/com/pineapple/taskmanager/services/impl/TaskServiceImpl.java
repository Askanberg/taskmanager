package com.pineapple.taskmanager.services.impl;

import com.pineapple.taskmanager.domain.entities.TaskEntity;
import com.pineapple.taskmanager.repositories.TaskRepository;
import com.pineapple.taskmanager.services.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskEntity createTask(TaskEntity taskEntity){
        return taskRepository.save(taskEntity);
    }
}
