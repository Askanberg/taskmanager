package com.pineapple.taskmanager.services;

import com.pineapple.taskmanager.entities.Task;
import com.pineapple.taskmanager.entities.User;
import com.pineapple.taskmanager.repositories.TaskRepository;
import com.pineapple.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Optional<Task> getAllTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
