package com.pineapple.taskmanager;

import com.pineapple.taskmanager.entities.Project;
import com.pineapple.taskmanager.entities.Task;
import com.pineapple.taskmanager.entities.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TestDataUtilities {

    private Collection<Task> tasksForTests = new ArrayList<>(Arrays.asList(createTestTaskA(), createTestTaskB(), createTestTaskC()));


    private TestDataUtilities() {

    }
    public static Task createTestTaskA() {
        return Task.builder()
                .id(1L)
                .title("TaskA")
                .description("ContentA")
                .completed(false)
                .dueDate(LocalDate.of(2024, 12, 8))
                .build();
    }
    public static Task createTestTaskB() {
        return Task.builder()
                .id(2L)
                .title("TaskB")
                .description("ContentB")
                .completed(true)
                .dueDate(LocalDate.of(2024, 12, 8))
                .build();
    }
    public static Task createTestTaskC() {
        return Task.builder()
                .id(3L)
                .title("TaskC")
                .description("ContentC")
                .completed(true)
                .dueDate(LocalDate.of(2024, 10, 1))
                .build();
    }

    public static User createTestUserA() {
        return User.builder()
                .id(1L)
                .username("userA")
                .password("passwordA")
                .tasks(Arrays.asList(createTestTaskA()))
                .build();

    }
    public static User createTestUserB() {
        return User.builder()
                .id(2L)
                .username("userB")
                .password("passwordB")
                .tasks(Arrays.asList(createTestTaskA(), createTestTaskB()))
                .build();
    }
    public static User createTestUserC() {
        return User.builder()
                .id(3L)
                .username("userC")
                .password("passwordC")
                .tasks(Arrays.asList(createTestTaskA(),createTestTaskB(),createTestTaskC()))
                .build();
    }

    public static Project createTestProjectA() {
        return Project.builder()
                .id(1L)
                .name("ProjectA")
                .tasks(Arrays.asList(createTestTaskA(),createTestTaskB(),createTestTaskC()))
                .build();
    }
}
