package com.ibay.backend.dao;

import com.ibay.backend.model.User;

import java.sql.Timestamp;
import java.util.Map;


public interface UserDao {

    Boolean columnContains(String table, String columnName, String value);

    Boolean insertUser(String id, Timestamp regTime, User user);

    User selectUserByID(String id);

    User selectUserByParams(Map<String, String> params);

    Boolean deleteUserByID(String id);

    Boolean updateUserByID(String id, Map<String, String> updateFields);
}
