package com.java.test.service;

import com.java.test.entity.User;
import com.java.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.rmi.NoSuchObjectException;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUSerById(Integer userId) throws NoSuchObjectException {
        return userRepository.getUserById(userId)
                .orElseThrow(() -> new NoSuchObjectException(String.format("User with id = %s not found in database.", userId)));
    }

    public void deleteUserById(Integer userId) throws NoSuchObjectException {
        int rowAffected = userRepository.deleteUserById(userId);
        if (rowAffected == 0)
            throw new NoSuchObjectException(String.format("User with id = %s not found in database.", userId));
    }

    public void addUser(User user) throws NoSuchObjectException {
        int rowAffected = userRepository.addUser(user);
        if (rowAffected == 0)
            throw new NoSuchObjectException(String.format("User with name = %s can not be added", user.getName()));

    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void updateUser(User user) throws NoSuchObjectException {
        int rowAffected = userRepository.updateUser(user);
        if (rowAffected == 0)
            throw new NoSuchObjectException(String.format("User with name = %s can not be added", user.getName()));
    }
}
