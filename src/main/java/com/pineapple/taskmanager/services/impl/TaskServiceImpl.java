package com.pineapple.taskmanager.services.impl;

import com.pineapple.taskmanager.domain.entities.TaskEntity;
import com.pineapple.taskmanager.repositories.TaskRepository;
import com.pineapple.taskmanager.services.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskEntity saveTask(TaskEntity taskEntity){
        return taskRepository.save(taskEntity);
    }

    @Override
    public List<TaskEntity> findAll() {
        return StreamSupport.stream(taskRepository.findAll()
                .spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TaskEntity> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public Optional<TaskEntity> findOne( Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id){
        return taskRepository.existsById(id);
    }

    @Override
    public TaskEntity partialUpdate(Long id, TaskEntity taskEntity) {
        taskEntity.setId(id);

        return taskRepository.findById(id).map(existingTask -> {
            Optional.ofNullable(taskEntity.getTitle()).ifPresent(existingTask::setTitle);
            Optional.ofNullable(taskEntity.getDescription()).ifPresent(existingTask::setDescription);
            return taskRepository.save(existingTask);
        }).orElseThrow(() -> new RuntimeException("Task does not exist!"));
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
