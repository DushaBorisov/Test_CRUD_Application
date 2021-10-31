package com.java.test.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;


@Configuration
@NoArgsConstructor
@PropertySource("classpath:application.properties")
public class JdbcConfig {

    private static final Integer MAX_POOL_SIZE = 10;


    @Value("${database.url}")
    private String DB_URl;

    @Value("${database.username}")
    private String USERNAME;

    @Value("${database.password}")
    private String PASSWORD;

    @Bean
    public DataSource postgresqlDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl(DB_URl);
        hikariConfig.setUsername(USERNAME);
        hikariConfig.setPassword(PASSWORD);
        hikariConfig.setMaximumPoolSize(MAX_POOL_SIZE);
        hikariConfig.setPoolName("springHikariCP");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}