package com.pineapple.taskmanager.controllers;

import com.pineapple.taskmanager.domain.dto.TaskDto;
import com.pineapple.taskmanager.domain.entities.TaskEntity;

import com.pineapple.taskmanager.mappers.Mapper;
import com.pineapple.taskmanager.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    private TaskService taskService;
    private Mapper<TaskEntity, TaskDto> taskMapper;

    public TaskController(TaskService taskService, Mapper<TaskEntity, TaskDto> taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }


    @PostMapping(path = "/tasks")
    public ResponseEntity<TaskDto> saveTask(@RequestBody TaskDto task) {
        TaskEntity taskEntity = taskMapper.mapFrom(task);
        TaskEntity savedTaskEntity = taskService.saveTask(taskEntity);
        return new ResponseEntity<>(taskMapper.mapTo(savedTaskEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/tasks")
    public List<TaskDto> listTasks() {
        List<TaskEntity> tasks = taskService.findAll();
        return tasks.stream()
                .map(taskMapper::mapTo)
                .collect(Collectors.toList());

    }

    @GetMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") Long id){
        Optional<TaskEntity> foundTask = taskService.findOne(id);
        return foundTask.map(taskEntity -> {
            TaskDto taskDto = taskMapper.mapTo(taskEntity);
            return new ResponseEntity<>(taskDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDto> fullUpdateTask(
            @PathVariable("id") Long id,
            @RequestBody TaskDto taskDto) {

        if (!taskService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        taskDto.setId(id);
        TaskEntity taskEntity = taskMapper.mapFrom(taskDto);
        TaskEntity savedTaskEntity = taskService.saveTask(taskEntity);
        return new ResponseEntity<>(taskMapper.mapTo(savedTaskEntity), HttpStatus.OK);
    }

}

