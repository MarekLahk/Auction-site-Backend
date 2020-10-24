package com.ibay.backend.dao.h2;


import com.ibay.backend.dao.postgres.UserDataAccessServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Profile("development")
@Repository
public class UserDataAccessServiceH2 extends UserDataAccessServicePostgres {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessServiceH2(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    //Overwrite any Postgres UserDataAccessService methods, that dont work with h2 here!




}
