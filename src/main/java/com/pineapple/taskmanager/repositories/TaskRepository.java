package com.pineapple.taskmanager.repositories;

import com.pineapple.taskmanager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByUserId(Long user_id);
}