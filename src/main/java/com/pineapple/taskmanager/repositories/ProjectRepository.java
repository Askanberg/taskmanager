package com.pineapple.taskmanager.repositories;

import com.pineapple.taskmanager.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByUserId(Long user_id);
}
