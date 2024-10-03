package com.pineapple.taskmanager.services;

import com.pineapple.taskmanager.entities.Project;
import com.pineapple.taskmanager.entities.User;
import com.pineapple.taskmanager.repositories.ProjectRepository;
import com.pineapple.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Optional<Project> getAllProjectsByUser(Long userId) {
        return projectRepository.findByUserId(userId);
    }

    public Project createProjcet(Project project) {
        return projectRepository.save(project);
    }

    public void deleteUser(Project project) {
        projectRepository.delete(project);
    }
}
