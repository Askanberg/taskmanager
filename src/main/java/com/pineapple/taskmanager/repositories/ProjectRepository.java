package com.pineapple.taskmanager.repositories;


import com.pineapple.taskmanager.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

}
