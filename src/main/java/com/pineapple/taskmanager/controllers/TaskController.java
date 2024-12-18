package com.pineapple.taskmanager.controllers;

import com.pineapple.taskmanager.domain.dto.TaskDto;
import com.pineapple.taskmanager.domain.entities.TaskEntity;

import com.pineapple.taskmanager.mappers.Mapper;
import com.pineapple.taskmanager.services.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<TaskDto> listTasks(Pageable pageable) {
        Page<TaskEntity> tasks = taskService.findAll(pageable);
        return tasks.map(taskMapper::mapTo);
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

    @PatchMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDto> partialUpdateTask(
            @PathVariable("id") Long id,
            @RequestBody TaskDto taskDto) {

        if (!taskService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TaskEntity taskEntity = taskMapper.mapFrom(taskDto);
        TaskEntity updatedTaskEntity = taskService.partialUpdate(id, taskEntity);
        return new ResponseEntity<>(taskMapper.mapTo(updatedTaskEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/tasks/{id}")
    public ResponseEntity<TaskDto> deleteTask(
            @PathVariable("id") Long id) {

        if (!taskService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

