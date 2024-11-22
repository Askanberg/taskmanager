package com.pineapple.taskmanager;

import com.pineapple.taskmanager.domain.dto.UserDto;
import com.pineapple.taskmanager.domain.entities.ProjectEntity;
import com.pineapple.taskmanager.domain.entities.TaskEntity;
import com.pineapple.taskmanager.domain.entities.UserEntity;

public class TestDataUtilities {

    private TestDataUtilities() {

    }
    public static TaskEntity createTestTaskEntityA(UserEntity userEntity) {
        return TaskEntity.builder()
                .id(1L)
                .title("TaskA")
                .description("ContentA")
                .completed(false)
                //.dueDate(LocalDate.of(2024, 12, 8))
                .userEntity(userEntity)
                .build();
    }
    public static TaskEntity createTestTaskDtoA(UserEntity userEntity) {
        return TaskEntity.builder()
                .id(1L)
                .title("TaskA")
                .description("ContentA")
                .completed(false)
                //.dueDate(LocalDate.of(2024, 12, 8))
                .userEntity(userEntity)
                .build();
    }
    public static TaskEntity createTestTaskB(UserEntity userEntity) {
        return TaskEntity.builder()
                .id(2L)
                .title("TaskB")
                .description("ContentB")
                .completed(true)
                //.dueDate(LocalDate.of(2024, 12, 8))
                .userEntity(userEntity)
                .build();
    }
    public static TaskEntity createTestTaskC(UserEntity userEntity) {
        return TaskEntity.builder()
                .id(3L)
                .title("TaskC")
                .description("ContentC")
                .completed(true)
                //.dueDate(LocalDate.of(2024, 10, 1))
                .userEntity(userEntity)
                .build();
    }

    public static UserEntity createTestUserA() {
        return UserEntity.builder()
                .id(1L)
                .username("UserA")
                .password("PasswordA")
                .build();

    }
    public static UserEntity createTestUserB() {
        return UserEntity.builder()
                .id(2L)
                .username("UserB")
                .password("PasswordB")
                .build();
    }
    public static UserEntity createTestUserC() {
        return UserEntity.builder()
                .id(3L)
                .username("UserC")
                .password("PasswordC")
                .build();
    }

    public static ProjectEntity createTestProjectA(UserEntity userEntity) {
        return ProjectEntity.builder()
                .id(1L)
                .name("ProjectA")
                .userEntity(userEntity)
                .build();
    }
    public static ProjectEntity createTestProjectB(UserEntity userEntity) {
        return ProjectEntity.builder()
                .id(2L)
                .name("ProjectB")
                .userEntity(userEntity)
                .build();
    }
}
