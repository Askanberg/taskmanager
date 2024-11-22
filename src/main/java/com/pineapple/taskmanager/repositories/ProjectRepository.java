package com.pineapple.taskmanager.repositories;


import com.pineapple.taskmanager.domain.entities.ProjectEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {

}
