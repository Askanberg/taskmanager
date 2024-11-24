package com.pineapple.taskmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pineapple.taskmanager.TestDataUtilities;
import com.pineapple.taskmanager.domain.entities.UserEntity;
import com.pineapple.taskmanager.services.UserService;
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
public class UserControllerIntegrationTests {

    private UserService userService;
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public UserControllerIntegrationTests(MockMvc mockMvc, ObjectMapper objectMapper, UserService userService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userService = userService;
    }

    @Test
    public void testThatCreateBookReturnsHttpStatus201Created() throws Exception {
        UserEntity userEntity = TestDataUtilities.createTestUserA();
        String userJson = objectMapper.writeValueAsString(userEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateUserReturnsSavedAuthor() throws Exception {
        UserEntity userEntity = TestDataUtilities.createTestUserA();
        userEntity.setId(0);
        String userJson = objectMapper.writeValueAsString(userEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.username").value("UserA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.password").value("PasswordA")
        );
    }

    @Test
    public void testThatListUsersReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListUsersReturnsListOfUsers() throws Exception {
        UserEntity testUserA = TestDataUtilities.createTestUserA();
        userService.createUser(testUserA);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].username").value("UserA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].password").value("PasswordA")
        );
    }

    @Test
    public void testThatGetUserReturnsHttpStatus200IfUserExist() throws Exception {
        UserEntity testUserA = TestDataUtilities.createTestUserA();
        userService.createUser(testUserA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetUserReturnsHttpStatus404IfUserDontExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
