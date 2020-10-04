package com.ibay.backend.dao;

import com.ibay.backend.model.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Timestamp;
import java.util.Optional;


public interface UserDao {

    Boolean columnContains(String table, String columnName, String value);

    Boolean insertUser(String id, Timestamp regTime, User user);

    User selectUserByID(String id);

    Boolean deleteUserByID(String id);

    Boolean updateUserByID(String id, User user);
}
