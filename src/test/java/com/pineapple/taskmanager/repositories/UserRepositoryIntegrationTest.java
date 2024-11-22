//package com.pineapple.taskmanager.repositories;
//
//import com.pineapple.taskmanager.TestDataUtilities;
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
//public class UserRepositoryIntegrationTest {
//
//    private UserRepository underTest;
//
//    @Autowired
//    public UserRepositoryIntegrationTest(UserRepository underTest) {
//        this.underTest = underTest;
//
//    }
//
//    @Test
//    public void testThatUserCanBeCreatedAndRecalled() {
//        UserEntity userEntity = TestDataUtilities.createTestUserA();
//        underTest.save(userEntity);
//
//        Optional<UserEntity> result = underTest.findById(userEntity.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(userEntity);
//    }
//
//    @Test
//    public void testThatMultipleUsersCanBeCreatedAndRecalled() {
//        UserEntity userEntityA = TestDataUtilities.createTestUserA();
//        underTest.save(userEntityA);
//        UserEntity userEntityB = TestDataUtilities.createTestUserB();
//        underTest.save(userEntityB);
//        UserEntity userEntityC = TestDataUtilities.createTestUserC();
//        underTest.save(userEntityC);
//
//        Iterable<UserEntity> result = underTest.findAll();
//        assertThat(result).
//                hasSize(3).
//                containsExactly(userEntityA, userEntityB, userEntityC);
//    }
//
//    @Test
//    public void testThatUserCanBeUpdated() {
//        UserEntity userEntityA = TestDataUtilities.createTestUserA();
//        underTest.save(userEntityA);
//
//        userEntityA.setUsername("UPDATED");
//        underTest.save(userEntityA);
//
//        Optional<UserEntity> result = underTest.findById(userEntityA.getId());
//
//        assertThat(result.get()).isEqualTo(userEntityA);
//    }
//
//    @Test
//    public void testThatUserCanBeDeleted() {
//        UserEntity userEntityA = TestDataUtilities.createTestUserA();
//        underTest.save(userEntityA);
//
//        underTest.deleteById(userEntityA.getId());
//
//        Optional<UserEntity> result = underTest.findById(userEntityA.getId());
//        assertThat(result).isEmpty();
//    }
//
//
//}
