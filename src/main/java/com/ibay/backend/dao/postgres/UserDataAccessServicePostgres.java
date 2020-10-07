package com.ibay.backend.dao.postgres;

import com.ibay.backend.dao.UserDao;
import com.ibay.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


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
    public Boolean insertUser(String id, Timestamp regTime, User user) {
        final String sqlQuery = String.format("INSERT INTO ibay_user (userID, username, full_name, email, registration_date)" +
                        " VALUES ('%s', '%s', '%s', '%s', '%s')",
                id, user.getUsername(), user.getFull_name(), user.getEmail(), regTime.toString());
        return jdbcTemplate.update(sqlQuery) > 0 ? Boolean.TRUE : Boolean.FALSE;

    }

//    @Override
//    public List<User> selectAllUsers() {
//        final String sqlQuery = "SELECT id, username FROM ibay_user";
//        return jdbcTemplate.query(sqlQuery, (resultSet, i) -> {
//            String id = resultSet.getString("id");
//            String username = resultSet.getString("username");
//            return new User(id, username, email, full_name, registrationDate);
//        });
//    }
    @Override
    public User selectUserByID(String id) {
        final String sqlQuery = String.format("SELECT * FROM ibay_user WHERE userID = '%s'", id);
        List<User> userList = jdbcTemplate.query(sqlQuery, (resultSet, i) -> {

            if (resultSet.getString("id") == null) {
                return null;
            } else return new User(
                    resultSet.getString("userID"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("full_name"),
                    resultSet.getTimestamp("registration_date")

                    );
        });
        return userList.size() > 0 ? userList.get(0) : null;
    }

    @Override
    public Boolean deleteUserByID(String id) {
        final String sqlQuery = String.format("DELETE FROM ibay_user WHERE userID = '%s'", id);
        return jdbcTemplate.update(sqlQuery) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean updateUserByID(String id, User user) {
        return false;
    }
}
