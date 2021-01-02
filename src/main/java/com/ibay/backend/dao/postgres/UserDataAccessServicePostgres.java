package com.ibay.backend.dao.postgres;

import com.ibay.backend.dao.UserDao;
import com.ibay.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


@Profile("production")
@Repository
public class UserDataAccessServicePostgres implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessServicePostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Boolean columnContains(String table, String columnName, String value) {
        final String sqlQuery = String.format(
                        "SELECT COUNT(%s) as Count FROM %s WHERE %s = '%s'"
                        , columnName, table, columnName, value);
        return jdbcTemplate.query(sqlQuery, (resultsSet, i) -> resultsSet.getInt("count")).get(0) > 0;
    }

    @Override
    public Boolean insertUser(String id, Timestamp regTime, User user, String passwordHash, String authorities) {
        final String sqlQuery = String.format("INSERT INTO ibay_user" +
                        " (userID, username, password, roles, full_name, email, registration_date)" +
                        " VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                id, user.getUsername(), passwordHash, authorities, user.getFull_name(), user.getEmail(), regTime.toString());
        return jdbcTemplate.update(sqlQuery) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<User> selectAllUsers() {
        final String sqlQuery = "SELECT * FROM ibay_user";
        return jdbcTemplate.query(sqlQuery, new User());
    }


    @Override
    public User selectUserByID(String id) {
        final String sqlQuery = String.format("SELECT * FROM ibay_user WHERE userID = '%s'", id);
        try {
            return jdbcTemplate.queryForObject(sqlQuery, new User());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User selectUserByUsername(String username) {
        final String sqlQuery = String.format("SELECT * FROM ibay_user WHERE username = '%s'", username);
        try {
            return jdbcTemplate.queryForObject(sqlQuery, new User());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User selectUserByParams(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM IBAY_USER WHERE ");
        String prefix = "";
        for (String param: params.keySet()) {
            sb.append(prefix).append(param).append("='").append(params.get(param)).append("'");
            prefix=",";
        }
        try {
            return jdbcTemplate.queryForObject(sb.toString(), new User());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Boolean deleteUserByID(String id) {
        final String sqlQuery = String.format("DELETE FROM ibay_user WHERE userID = '%s'", id);
        return jdbcTemplate.update(sqlQuery) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean updateUserByID(String id, Map<String, String> updateFields) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ibay_user SET ");
        String prefix = "";
        for (String key : updateFields.keySet()) {
            sb.append(prefix).append(key).append("='").append(updateFields.get(key)).append("'");
            prefix=" and ";
        }
        sb.append(" WHERE userid = '").append(id).append("'");
        return jdbcTemplate.update(sb.toString()) > 0? Boolean.TRUE : Boolean.FALSE;
    }
}
