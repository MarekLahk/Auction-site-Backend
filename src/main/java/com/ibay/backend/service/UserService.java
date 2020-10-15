package com.ibay.backend.service;

import com.ibay.backend.dao.UserDao;
import com.ibay.backend.exceptions.generalExceptions.IdGenerationException;
import com.ibay.backend.exceptions.userExceptions.EmailTakenException;
import com.ibay.backend.exceptions.userExceptions.UsernameTakenException;
import com.ibay.backend.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;


@Service
public class UserService {

    private final UserDao userDao;
    private final IdGenerator idGenerator;

    @Autowired
    public UserService(UserDao userDao, IdGenerator idGenerator) {
        this.userDao = userDao;
        this.idGenerator = idGenerator;
    }




    private String generateUniqueID() {
        String id;
        int counter = 0;
        do {
            id = idGenerator.generateStringID(12);
            counter++;
        } while (userDao.columnContains("ibay_user","userID", id) && counter < 10);
        if (counter >= 10) throw new IdGenerationException();
        return id;
    }

    public String addUser(User user) {
        if (userDao.columnContains("ibay_user", "username", user.getUsername())) { throw new UsernameTakenException(); }
        if (userDao.columnContains("ibay_user", "email", user.getEmail())) { throw new EmailTakenException(); }
        final var id = generateUniqueID();
        if (userDao.insertUser(id,new Timestamp(System.currentTimeMillis()), user)) return id;
        return null;
    }


    public User getUserByID(String id) {
        return userDao.selectUserByID(id);
    }

    public Boolean deleteUserByID(String id) {
        return userDao.deleteUserByID(id);
    }

    public Boolean updateUserByID(String id, User user) {
        final Map<String, String> updateFields = user.getUpdateFields();
        if (updateFields.containsKey("username")) {
            if (userDao.columnContains("ibay_user", "username", updateFields.get("username"))) {
                throw new UsernameTakenException();
            }
        }
        if (updateFields.containsKey("email")) {
            if (userDao.columnContains("ibay_user", "email", updateFields.get("email"))) {
                throw new EmailTakenException();
            }
        }
        return userDao.updateUserByID(id, updateFields);
    }
}
