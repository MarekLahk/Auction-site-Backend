package com.ibay.backend.dao.postgres;

import com.ibay.backend.dao.AuthDao;
import com.ibay.backend.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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
        final String sqlQuery = String.format("SELECT EXISTS (SELECT 1 FROM authtoken WHERE tokenid = '%s')", id);
        return !jdbcTemplate.queryForObject(sqlQuery, Boolean.class);
    }

    @Override
    public void addTokenInactive(String tokenID, Timestamp expirationDate) {
        final String sqlQuery = String.format(
                "INSERT INTO authtoken (tokenid, validuntil) VALUES ('%s', '%s')",
                tokenID, expirationDate);

        System.out.println(new Timestamp(System.currentTimeMillis()));
        jdbcTemplate.execute(sqlQuery);
    }
}
