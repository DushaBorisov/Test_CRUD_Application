package com.java.test.repository.impl;

import com.java.test.Main;
import com.java.test.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserRepositoryImplTest {

    static UserRepositoryImpl userRepository;

    private static final User testUser = User.builder()
            .name("Andrey")
            .surname("Borisov")
            .age(19)
            .login("andrey")
            .password("123456")
            .build();

    private static User newUser = User.builder()
            .name("Anton")
            .surname("Petrov")
            .age(22)
            .login("anton")
            .password("123456")
            .build();

    @BeforeAll
    static void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        userRepository = context.getBean(UserRepositoryImpl.class);
        assertNotNull(userRepository);
    }

    @Test
    void getExistingUserByIdTest() {
        User user = userRepository.getUserById(1).get();
        assertThat(user, equalTo(testUser));
    }

    @Test
    void getAllUsersTest() {
        List<User> userList = userRepository.getAllUsers();
        assertThat(userList, contains(testUser));
    }

    @Test
    void addAndDeleteUserByIdTest() {
        Integer userId = userRepository.addUser(newUser);
        assertNotNull(userId);
        Integer rowAffected = userRepository.deleteUserById(userId);
        assertEquals(1, rowAffected);
    }

    @Test
    void updateUserTest(){
        Integer userId = userRepository.addUser(newUser);
        assertNotNull(userId);

        newUser.setName("Danil");
        Integer rowAffected = userRepository.updateUser(newUser);
        assertNotNull(rowAffected);

        User user = userRepository.getUserById(userId).get();
        assertThat((user.getName()), equalTo("Danil"));

        assertThat(userRepository.deleteUserById(userId), equalTo(1));
    }
}