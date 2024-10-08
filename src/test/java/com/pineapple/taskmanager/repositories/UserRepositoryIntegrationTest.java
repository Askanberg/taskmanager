package com.pineapple.taskmanager.repositories;

import com.pineapple.taskmanager.TestDataUtilities;
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
public class UserRepositoryIntegrationTest {

    private UserRepository underTest;

    @Autowired
    public UserRepositoryIntegrationTest(UserRepository underTest) {
        this.underTest = underTest;

    }

    @Test
    public void testThatUserCanBeCreatedAndRecalled() {
        User user = TestDataUtilities.createTestUserA();
        underTest.save(user);

        Optional<User> result = underTest.findById(user.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }

    @Test
    public void testThatMultipleUsersCanBeCreatedAndRecalled() {
        User userA = TestDataUtilities.createTestUserA();
        underTest.save(userA);
        User userB = TestDataUtilities.createTestUserB();
        underTest.save(userB);
        User userC = TestDataUtilities.createTestUserC();
        underTest.save(userC);

        Iterable<User> result = underTest.findAll();
        assertThat(result).
                hasSize(3).
                containsExactly(userA, userB, userC);
    }

    @Test
    public void testThatUserCanBeUpdated() {
        User userA = TestDataUtilities.createTestUserA();
        underTest.save(userA);

        userA.setUsername("UPDATED");
        underTest.save(userA);

        Optional<User> result = underTest.findById(userA.getId());

        assertThat(result.get()).isEqualTo(userA);
    }

    @Test
    public void testThatUserCanBeDeleted() {
        User userA = TestDataUtilities.createTestUserA();
        underTest.save(userA);

        underTest.deleteById(userA.getId());

        Optional<User> result = underTest.findById(userA.getId());
        assertThat(result).isEmpty();
    }


}
