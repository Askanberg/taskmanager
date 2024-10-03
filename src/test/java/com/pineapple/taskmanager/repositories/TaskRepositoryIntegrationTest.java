package com.pineapple.taskmanager.repositories;

import com.pineapple.taskmanager.TestDataUtilities;
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
public class TaskRepositoryIntegrationTest {

    private TaskRepository underTest;

    @Autowired
    public TaskRepositoryIntegrationTest(TaskRepository underTest) {
        this.underTest = underTest;

    }

    @Test
    public void testThatUserCanBeCreatedAndRecalled() {
        User user = TestDataUtilities.createTestUserA();
        Task task = TestDataUtilities.createTestTaskA(user);
        underTest.save(task);

        Optional<Task> result = underTest.findById(task.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(task);
    }

    @Test
    public void testThatMultipleTasksCanBeCreatedAndRecalled() {
        User user = TestDataUtilities.createTestUserA();

        Task taskA = TestDataUtilities.createTestTaskA(user);
        underTest.save(taskA);
        Task taskB = TestDataUtilities.createTestTaskB(user);
        underTest.save(taskB);
        Task taskC = TestDataUtilities.createTestTaskC(user);
        underTest.save(taskC);

        Iterable<Task> result = underTest.findAll();

        assertThat(result)
                .hasSize(3)
                .containsExactly(taskA, taskB, taskC);
    }

    @Test
    public void testThatTaskCanBeUpdated() {
        User user = TestDataUtilities.createTestUserA();

        Task taskA = TestDataUtilities.createTestTaskA(user);
        taskA.setTitle("UPDATED");
        underTest.save(taskA);

        Optional<Task> result = underTest.findById(taskA.getId());

        assertThat(result.get()).isEqualTo(taskA);
    }

    @Test
    public void testThatTaskCanBeDeleted() {
        User user = TestDataUtilities.createTestUserA();

        Task taskA = TestDataUtilities.createTestTaskA(user);
        underTest.save(taskA);

        underTest.deleteById(taskA.getId());

        Optional<Task> result = underTest.findById(taskA.getId());

        assertThat(result).isEmpty();
    }

}
