package com.java.test.repository;

import com.java.test.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository {

    Optional<User> getUserById(Integer userId);
    List<User> getAllUsers();
    int deleteUserById(Integer userId);
    int addUser(User user);
    int updateUser(User user);
}
