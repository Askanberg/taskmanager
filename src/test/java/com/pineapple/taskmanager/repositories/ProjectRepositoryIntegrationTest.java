//package com.pineapple.taskmanager.repositories;
//
//import com.pineapple.taskmanager.TestDataUtilities;
//import com.pineapple.taskmanager.domain.entities.ProjectEntity;
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
//public class ProjectRepositoryIntegrationTest {
//
//    private ProjectRepository underTest;
//
//    @Autowired
//    public ProjectRepositoryIntegrationTest(ProjectRepository underTest) {
//        this.underTest = underTest;
//
//    }
//
//    @Test
//    public void testThatProjectCanBeCreatedAndRecalled() {
//        UserEntity userEntity = TestDataUtilities.createTestUserA();
//
//        ProjectEntity projectEntity = TestDataUtilities.createTestProjectA(userEntity);
//        underTest.save(projectEntity);
//
//        Optional<ProjectEntity> result = underTest.findById(projectEntity.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(projectEntity);
//    }
//
//    @Test
//    public void testThatMultipleProjectsCanBeCreatedAndRecalled() {
//        UserEntity userEntity = TestDataUtilities.createTestUserA();
//
//        ProjectEntity projectEntityA = TestDataUtilities.createTestProjectA(userEntity);
//        underTest.save(projectEntityA);
//
//        ProjectEntity projectEntityB = TestDataUtilities.createTestProjectB(userEntity);
//        underTest.save(projectEntityB);
//
//        Iterable<ProjectEntity> result = underTest.findAll();
//
//        assertThat(result).
//                hasSize(2)
//                .containsExactlyInAnyOrder(projectEntityA, projectEntityB);
//    }
//
//    @Test
//    public void testThatProjectCanBeUpdated() {
//        UserEntity userEntity = TestDataUtilities.createTestUserA();
//
//        ProjectEntity projectEntityA = TestDataUtilities.createTestProjectA(userEntity);
//        projectEntityA.setName("UPDATED");
//        underTest.save(projectEntityA);
//
//        Optional<ProjectEntity> result = underTest.findById(projectEntityA.getId());
//
//        assertThat(result.get()).isEqualTo(projectEntityA);
//    }
//
//    @Test
//    public void testThatProjectCanBeDeleted() {
//        UserEntity userEntity = TestDataUtilities.createTestUserA();
//
//        ProjectEntity projectEntityA = TestDataUtilities.createTestProjectA(userEntity);
//        underTest.save(projectEntityA);
//
//        underTest.deleteById(projectEntityA.getId());
//
//        Optional<ProjectEntity> result = underTest.findById(projectEntityA.getId());
//
//        assertThat(result).isEmpty();
//    }
//
//
//}
