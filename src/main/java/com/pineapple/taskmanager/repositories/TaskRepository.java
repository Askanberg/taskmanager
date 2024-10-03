package com.pineapple.taskmanager.repositories;


import com.pineapple.taskmanager.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}