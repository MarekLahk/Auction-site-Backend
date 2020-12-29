package com.ibay.backend.service;

import com.ibay.backend.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class AdminUserService {

    private final UserDao userDao;

    @Autowired
    public AdminUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void deleteUserByID(String id) {
        userDao.deleteUserByID(id);
    }
}
