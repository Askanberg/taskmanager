package com.pineapple.taskmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pineapple.taskmanager.TestDataUtilities;
import com.pineapple.taskmanager.domain.entities.TaskEntity;
import com.pineapple.taskmanager.domain.entities.UserEntity;
import com.pineapple.taskmanager.services.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class TaskControllerIntegrationTests {

    private TaskService taskService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    public TaskControllerIntegrationTests(MockMvc mockMvc, TaskService taskService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.taskService = taskService;
    }

    @Test
    public void testThatCreateTaskReturnsHttp201Created() throws Exception {
        TaskEntity testTaskA = TestDataUtilities.createTestTaskEntityA(new UserEntity());
        testTaskA.setId(0);
        String taskJson = objectMapper.writeValueAsString(testTaskA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateTaskReturnsSavedAuthor() throws Exception {
        TaskEntity testTaskA = TestDataUtilities.createTestTaskEntityA(new UserEntity());
        testTaskA.setId(0);
        String taskJson = objectMapper.writeValueAsString(testTaskA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("TaskA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.description").value("ContentA")
        );
    }

    @Test
    public void testThatListTasksReturnsHttpStatus200 () throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testThatListTasksReturnsListOfTasks() throws Exception {
        TaskEntity testTaskA = TestDataUtilities.createTestTaskEntityA(TestDataUtilities.createTestUserA());
        taskService.createTask(testTaskA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value("TaskA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].description").value("ContentA")
        );
    }

    @Test
    public void testThatGetUserReturnsHttpStatus200IfUserExists () throws Exception {
        TaskEntity testTaskA = TestDataUtilities.createTestTaskEntityA(TestDataUtilities.createTestUserA());
        taskService.createTask(testTaskA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetUserReturnsHttpStatus404IfUserDontExist () throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/tasks/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
