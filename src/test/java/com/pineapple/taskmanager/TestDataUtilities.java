package com.pineapple.taskmanager;

import com.pineapple.taskmanager.domain.Project;
import com.pineapple.taskmanager.domain.Task;
import com.pineapple.taskmanager.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class TestDataUtilities {

    private TestDataUtilities() {

    }
    public static Task createTestTaskA(User user) {
        return Task.builder()
                .id(1L)
                .title("TaskA")
                .description("ContentA")
                .completed(false)
                .dueDate(LocalDate.of(2024, 12, 8))
                .user(user)
                .build();
    }
    public static Task createTestTaskB(User user) {
        return Task.builder()
                .id(2L)
                .title("TaskB")
                .description("ContentB")
                .completed(true)
                .dueDate(LocalDate.of(2024, 12, 8))
                .user(user)
                .build();
    }
    public static Task createTestTaskC(User user) {
        return Task.builder()
                .id(3L)
                .title("TaskC")
                .description("ContentC")
                .completed(true)
                .dueDate(LocalDate.of(2024, 10, 1))
                .user(user)
                .build();
    }

    public static User createTestUserA() {
        return User.builder()
                .id(1L)
                .username("userA")
                .password("passwordA")
                .tasks(new ArrayList<>())
                .build();

    }
    public static User createTestUserB() {
        return User.builder()
                .id(2L)
                .username("userB")
                .password("passwordB")
                .tasks(new ArrayList<>())
                .build();
    }
    public static User createTestUserC() {
        return User.builder()
                .id(3L)
                .username("userC")
                .password("passwordC")
                .tasks(new ArrayList<>())
                .build();
    }

    public static Project createTestProjectA(User user) {
        return Project.builder()
                .id(1L)
                .name("ProjectA")
                .tasks(new ArrayList<>())
                .user(user)
                .build();
    }
    public static Project createTestProjectB(User user) {
        return Project.builder()
                .id(2L)
                .name("ProjectB")
                .tasks(new ArrayList<>())
                .user(user)
                .build();
    }
}
