package com.pineapple.taskmanager.repositories;

import com.pineapple.taskmanager.domain.entities.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Long>,
        PagingAndSortingRepository<TaskEntity, Long> {
}