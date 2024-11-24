package com.pineapple.taskmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pineapple.taskmanager.TestDataUtilities;
import com.pineapple.taskmanager.domain.entities.ProjectEntity;
import com.pineapple.taskmanager.domain.entities.TaskEntity;
import com.pineapple.taskmanager.domain.entities.UserEntity;
import com.pineapple.taskmanager.services.ProjectService;
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
public class ProjectControllerIntegrationTests {

    private ProjectService projectService;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public ProjectControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, ProjectService projectService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.projectService = projectService;
    }

    @Test
    public void testThatCreateProjectReturnsHttpStatus201Created() throws Exception {
        ProjectEntity projectEntity = TestDataUtilities.createTestProjectA(null);
        String projectJson = objectMapper.writeValueAsString(projectEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateProjectReturnsSavedAuthor() throws Exception {
        ProjectEntity projectEntity = TestDataUtilities.createTestProjectA(null);
        projectEntity.setId(0);
        String projectJson = objectMapper.writeValueAsString(projectEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("ProjectA")
        );
    }

    @Test
    public void testThatListProjectsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListProjectsReturnsListOfProjects() throws Exception {
        ProjectEntity testProjectA = TestDataUtilities.createTestProjectA(TestDataUtilities.createTestUserA());
        projectService.createProject(testProjectA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("ProjectA")
        );
    }

    @Test
    public void testThatGetProjectReturnsHttpStatus200IfUserExists() throws Exception {
        ProjectEntity testProjectA = TestDataUtilities.createTestProjectA(TestDataUtilities.createTestUserA());
        projectService.createProject(testProjectA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/projects/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetProjectReturnsHttpStatus404IfUserDontExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/projects/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
