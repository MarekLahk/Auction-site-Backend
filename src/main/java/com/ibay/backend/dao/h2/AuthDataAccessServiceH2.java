package com.ibay.backend.dao.h2;


import com.ibay.backend.dao.postgres.AuthDataAccessServicePostgres;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Profile("development")
@Repository
public class AuthDataAccessServiceH2 extends AuthDataAccessServicePostgres {


    private final JdbcTemplate jdbcTemplate;

    public AuthDataAccessServiceH2(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }
}
