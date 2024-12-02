package com.pineapple.taskmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pineapple.taskmanager.TestDataUtilities;
import com.pineapple.taskmanager.domain.dto.ProjectDto;
import com.pineapple.taskmanager.domain.entities.ProjectEntity;
import com.pineapple.taskmanager.domain.entities.TaskEntity;
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
        ProjectEntity projectEntity = TestDataUtilities.createTestProjectEntityA(null);
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
        ProjectEntity projectEntity = TestDataUtilities.createTestProjectEntityA(null);
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
        ProjectEntity testProjectA = TestDataUtilities.createTestProjectEntityA(TestDataUtilities.createTestUserEntityA());
        projectService.saveProject(testProjectA);
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
        ProjectEntity testProjectA = TestDataUtilities.createTestProjectEntityA(TestDataUtilities.createTestUserEntityA());
        projectService.saveProject(testProjectA);

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

    @Test
    public void testThatFullUpdateProjectReturnsHttpStatus404IfUserDontExist() throws Exception {
        ProjectDto projectDto = TestDataUtilities.createTestProjectDtoA(TestDataUtilities.createTestUserEntityA());
        String projectDtoJson = objectMapper.writeValueAsString(projectDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/projects/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatFullUpdateProjectReturnsHttpStatus200IfUserExists() throws Exception {
        ProjectEntity testProjectA = TestDataUtilities.createTestProjectEntityA(TestDataUtilities.createTestUserEntityA());
        ProjectEntity savedTestProject = projectService.saveProject(testProjectA);

        ProjectDto projectDto = TestDataUtilities.createTestProjectDtoA(TestDataUtilities.createTestUserEntityA());

        String projectDtoJson = objectMapper.writeValueAsString(projectDto);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/projects/" + savedTestProject.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatFullUpdateProjectUpdatesExistingUser() throws Exception {
        ProjectEntity testProjectA = TestDataUtilities.createTestProjectEntityA(TestDataUtilities.createTestUserEntityA());
        ProjectEntity savedTestProjectA = projectService.saveProject(testProjectA);

        ProjectDto projectDto = TestDataUtilities.createTestProjectDtoA(TestDataUtilities.createTestUserEntityA());
        projectDto.setId(testProjectA.getId());

        String projectUpdateDtoJson = objectMapper.writeValueAsString(projectDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/projects/" + savedTestProjectA.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(projectUpdateDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedTestProjectA.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(projectDto.getName())
        );
    }

}
