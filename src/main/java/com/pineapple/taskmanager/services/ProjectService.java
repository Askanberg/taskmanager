package com.pineapple.taskmanager.services;

import com.pineapple.taskmanager.domain.Project;
import com.pineapple.taskmanager.domain.User;
import com.pineapple.taskmanager.repositories.ProjectRepository;
import com.pineapple.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
/*
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Optional<Project> getAllProjectsByUser(Long userId) {
        return projectRepository.findByUserId(userId);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteUser(Project project) {
        projectRepository.delete(project);
    }


}
*/