//package com.pineapple.taskmanager.repositories;
//
//import com.pineapple.taskmanager.TestDataUtilities;
//import com.pineapple.taskmanager.domain.entities.TaskEntity.java;
//import com.pineapple.taskmanager.domain.entities.UserEntity;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@Transactional
//public class TaskRepositoryIntegrationTest {
//
//    private TaskRepository underTest;
//
//    @Autowired
//    public TaskRepositoryIntegrationTest(TaskRepository underTest) {
//        this.underTest = underTest;
//
//    }
//
//    @Test
//    public void testThatUserCanBeCreatedAndRecalled() {
//        UserEntity userEntity = TestDataUtilities.createTestUserA();
//        TaskEntity.java taskEntity = TestDataUtilities.createTestTaskA(userEntity);
//        underTest.save(taskEntity);
//
//        Optional<TaskEntity.java> result = underTest.findById(taskEntity.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(taskEntity);
//    }
//
//    @Test
//    public void testThatMultipleTasksCanBeCreatedAndRecalled() {
//        UserEntity userEntity = TestDataUtilities.createTestUserA();
//
//        TaskEntity.java taskEntityA = TestDataUtilities.createTestTaskA(userEntity);
//        underTest.save(taskEntityA);
//        TaskEntity.java taskEntityB = TestDataUtilities.createTestTaskB(userEntity);
//        underTest.save(taskEntityB);
//        TaskEntity.java taskEntityC = TestDataUtilities.createTestTaskC(userEntity);
//        underTest.save(taskEntityC);
//
//        Iterable<TaskEntity.java> result = underTest.findAll();
//
//        assertThat(result)
//                .hasSize(3)
//                .containsExactly(taskEntityA, taskEntityB, taskEntityC);
//    }
//
//    @Test
//    public void testThatTaskCanBeUpdated() {
//        UserEntity userEntity = TestDataUtilities.createTestUserA();
//
//        TaskEntity.java taskEntityA = TestDataUtilities.createTestTaskA(userEntity);
//        taskEntityA.setTitle("UPDATED");
//        underTest.save(taskEntityA);
//
//        Optional<TaskEntity.java> result = underTest.findById(taskEntityA.getId());
//
//        assertThat(result.get()).isEqualTo(taskEntityA);
//    }
//
//    @Test
//    public void testThatTaskCanBeDeleted() {
//        UserEntity userEntity = TestDataUtilities.createTestUserA();
//
//        TaskEntity.java taskEntityA = TestDataUtilities.createTestTaskA(userEntity);
//        underTest.save(taskEntityA);
//
//        underTest.deleteById(taskEntityA.getId());
//
//        Optional<TaskEntity.java> result = underTest.findById(taskEntityA.getId());
//
//        assertThat(result).isEmpty();
//    }
//
//}
