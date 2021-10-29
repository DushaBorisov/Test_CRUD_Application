package com.java.test.repository.impl;

import com.java.test.entity.User;
import com.java.test.repository.UserRepository;
import com.java.test.repository.mappers.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_QUERY_SELECT_USER_BY_ID = "SELECT * FROM public.users WHERE id = ?";
    private static final String SQL_QUERY_CREATE_NEW_USER = "INSERT INTO public.users(name, surname, age, login, password) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_QUERY_DELETE_USER_BY_ID = "DELETE FROM public.users WHERE id = ?";
    private static final String SQL_QUERY_SELECT_ALL_USERS = "SELECT * FROM public.users";
    private static final String SQL_QUERY_UPDATE_USER = "UPDATE public.users SET name = ?, surname = ?, age = ?, login = ?, password = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> getUserById(Integer userId) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_QUERY_SELECT_USER_BY_ID, new UserRowMapper(), userId));
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.queryForList(SQL_QUERY_SELECT_ALL_USERS, User.class);
    }

    @Override
    public int deleteUserById(Integer userId) {
        return jdbcTemplate.update(SQL_QUERY_DELETE_USER_BY_ID, userId);
    }

    @Override
    public int addUser(User user) {
       return jdbcTemplate.update(SQL_QUERY_CREATE_NEW_USER, user.getName(), user.getSurname(), user.getAge(), user.getLogin(), user.getPassword());
    }

    @Override
    public int updateUser(User user) {
        return jdbcTemplate.update(SQL_QUERY_UPDATE_USER, user.getName(), user.getSurname(), user.getAge(), user.getLogin(), user.getPassword());

    }
}
