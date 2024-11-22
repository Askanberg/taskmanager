package com.pineapple.taskmanager.domain.dto;

import com.pineapple.taskmanager.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private long id;

    private String title;
    private String description;

    private boolean completed;
    private LocalDate dueDate;

    private UserEntity userEntity;
    //private ProjectEntity projectEntity;

}
