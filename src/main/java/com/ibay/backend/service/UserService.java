package com.ibay.backend.service;

import com.ibay.backend.dao.UserDao;
import com.ibay.backend.exceptions.userExceptions.EmailTakenException;
import com.ibay.backend.exceptions.userExceptions.UsernameTakenException;
import com.ibay.backend.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class UserService {

    private final UserDao userDao;



    @Autowired
    public UserService( UserDao userDao) {
        this.userDao = userDao;
    }



    private String generateUniqueID() {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(8);
        } while (userDao.columnContains("ibay_user","userID", id));
        return id;
    }

    public Boolean addUser(User user) {
        if (userDao.columnContains("ibay_user", "username", user.getUsername())) { throw new UsernameTakenException(); }
        if (userDao.columnContains("ibay_user", "email", user.getEmail())) { throw new EmailTakenException(); }
        return userDao.insertUser(generateUniqueID(),new Timestamp(System.currentTimeMillis()), user);
    }


    public User getUserByID(String id) {
        return userDao.selectUserByID(id);
    }

    public Boolean deleteUserByID(String id) {
        return userDao.deleteUserByID(id);
    }

    public Boolean updateUserByID(String id, User user) {
        return userDao.updateUserByID(id, user);
    }
}
