package com.java.test.repository.impl;

import com.java.test.entity.User;
import com.java.test.repository.UserRepository;
import com.java.test.repository.mappers.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_QUERY_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = :id";
    private static final String SQL_QUERY_INSERT_NEW_USER = "INSERT INTO users(name, surname, age, login, password) VALUES( :name, :surname, :age, :login, :password)";
    private static final String SQL_QUERY_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = :id";
    private static final String SQL_QUERY_SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_QUERY_UPDATE_USER = "UPDATE users SET name = :name, surname = :surname, age = :age, login = :login, password = :password WHERE login = :login AND password = :password";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Optional<User> getUserById(Integer userId) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", userId);

        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(SQL_QUERY_SELECT_USER_BY_ID, params, new UserRowMapper()));
    }

    @Override
    public List<User> getAllUsers() {
        return namedParameterJdbcTemplate.query(SQL_QUERY_SELECT_ALL_USERS, new HashMap<>(), new UserRowMapper());
    }

    @Override
    public int deleteUserById(Integer userId) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("id", userId);

        return namedParameterJdbcTemplate.update(SQL_QUERY_DELETE_USER_BY_ID, params);
    }

    @Override
    public int addUser(User user) {
        Map<String, Object> params = new HashMap<>(5);
        params.put("name", user.getName());
        params.put("surname", user.getSurname());
        params.put("age", user.getAge());
        params.put("login", user.getLogin());
        params.put("password", user.getPassword());

        return namedParameterJdbcTemplate.update(SQL_QUERY_INSERT_NEW_USER, params);
    }

    @Override
    public int updateUser(User user) {
        Map<String, Object> params = new HashMap<>(5);
        params.put("name", user.getName());
        params.put("surname", user.getSurname());
        params.put("age", user.getAge());
        params.put("login", user.getLogin());
        params.put("password", user.getPassword());

        return namedParameterJdbcTemplate.update(SQL_QUERY_UPDATE_USER, params);
    }
}
