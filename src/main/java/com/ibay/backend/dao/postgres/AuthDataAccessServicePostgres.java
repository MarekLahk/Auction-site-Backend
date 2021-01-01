package com.ibay.backend.dao.postgres;

import com.ibay.backend.dao.AuthDao;
import com.ibay.backend.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Profile("production")
@Repository
public class AuthDataAccessServicePostgres implements AuthDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthDataAccessServicePostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<AuthUser> selectUserByUsername(String username) {
        final String sqlQuery = String.format("SELECT userid, username, password, roles FROM ibay_user WHERE username = '%s'", username);
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sqlQuery, new AuthUser()));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Boolean isTokenActive(String id) {
        System.out.println("Checking token active");
        final String sqlQuery = String.format("SELECT EXISTS (SELECT 1 FROM authtoken WHERE tokenid = '%s')", id);
        return !jdbcTemplate.queryForObject(sqlQuery, Boolean.class);
    }
}
