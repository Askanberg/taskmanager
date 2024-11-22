package com.pineapple.taskmanager.domain.dto;

import com.pineapple.taskmanager.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDto {
    private long id;

    private String name;

    private UserDto userEntity;

}
