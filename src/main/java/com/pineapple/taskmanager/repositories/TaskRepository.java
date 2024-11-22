package com.pineapple.taskmanager.repositories;


import com.pineapple.taskmanager.domain.entities.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
}