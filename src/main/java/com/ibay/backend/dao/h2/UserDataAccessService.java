package com.ibay.backend.dao.h2;


import com.ibay.backend.dao.UserDao;
import com.ibay.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Profile("development")
@Repository
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        System.out.println("works");
    }

    @Override
    public Boolean columnContains(String table, String columnName, String value) {
        return null;
    }

    @Override
    public Boolean insertUser(String id, Timestamp regTime, User user) {
        return null;
    }

    @Override
    public User selectUserByID(String id) {
        return null;
    }

    @Override
    public Boolean deleteUserByID(String id) {
        return null;
    }

    @Override
    public Boolean updateUserByID(String id, User user) {
        return null;
    }
}
