package com.pineapple.taskmanager.repositories;

import com.pineapple.taskmanager.TestDataUtilities;
import com.pineapple.taskmanager.domain.Project;
import com.pineapple.taskmanager.domain.Task;
import com.pineapple.taskmanager.domain.User;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ProjectRepositoryIntegrationTest {

    private ProjectRepository underTest;

    @Autowired
    public ProjectRepositoryIntegrationTest(ProjectRepository underTest) {
        this.underTest = underTest;

    }

    @Test
    public void testThatProjectCanBeCreatedAndRecalled() {
        User user = TestDataUtilities.createTestUserA();

        Project project = TestDataUtilities.createTestProjectA(user);
        underTest.save(project);

        Optional<Project> result = underTest.findById(project.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(project);
    }

    @Test
    public void testThatMultipleProjectsCanBeCreatedAndRecalled() {
        User user = TestDataUtilities.createTestUserA();

        Project projectA = TestDataUtilities.createTestProjectA(user);
        underTest.save(projectA);

        Project projectB = TestDataUtilities.createTestProjectB(user);
        underTest.save(projectB);

        Iterable<Project> result = underTest.findAll();

        assertThat(result).
                hasSize(2)
                .containsExactlyInAnyOrder(projectA, projectB);
    }

    @Test
    public void testThatProjectCanBeUpdated() {
        User user = TestDataUtilities.createTestUserA();

        Project projectA = TestDataUtilities.createTestProjectA(user);
        projectA.setName("UPDATED");
        underTest.save(projectA);

        Optional<Project> result = underTest.findById(projectA.getId());

        assertThat(result.get()).isEqualTo(projectA);
    }

    @Test
    public void testThatProjectCanBeDeleted() {
        User user = TestDataUtilities.createTestUserA();

        Project projectA = TestDataUtilities.createTestProjectA(user);
        underTest.save(projectA);

        underTest.deleteById(projectA.getId());

        Optional<Project> result = underTest.findById(projectA.getId());

        assertThat(result).isEmpty();
    }


}
