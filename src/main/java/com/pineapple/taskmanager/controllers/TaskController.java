package com.pineapple.taskmanager.controllers;

import com.pineapple.taskmanager.domain.dto.TaskDto;
import com.pineapple.taskmanager.domain.entities.TaskEntity;

import com.pineapple.taskmanager.mappers.Mapper;
import com.pineapple.taskmanager.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private TaskService taskService;
    private Mapper<TaskEntity, TaskDto> taskMapper;

    public TaskController(TaskService taskService, Mapper<TaskEntity, TaskDto> taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }


    @PostMapping(path = "/tasks")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto task) {
        TaskEntity taskEntity = taskMapper.mapFrom(task);
        TaskEntity savedTaskEntity = taskService.createTask(taskEntity);
        return new ResponseEntity<>(taskMapper.mapTo(savedTaskEntity), HttpStatus.CREATED);
    }

}

