package com.java.test.repository.mappers;

import com.java.test.repository.entity.NewUserId;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewUserIdRowMapper implements RowMapper<NewUserId> {
    @Override
    public NewUserId mapRow(ResultSet rs, int rowNum) throws SQLException {
        return NewUserId.builder()
                .id(rs.getInt("id"))
                .build();
    }
}
