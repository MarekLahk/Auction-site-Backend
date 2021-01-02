package com.ibay.backend.service;

import com.ibay.backend.dao.UserDao;
import com.ibay.backend.exceptions.generalExceptions.IdGenerationException;
import com.ibay.backend.exceptions.userExceptions.EmailTakenException;
import com.ibay.backend.exceptions.userExceptions.UserInvalidParametersException;
import com.ibay.backend.exceptions.userExceptions.UsernameTakenException;
import com.ibay.backend.model.User;
import com.ibay.backend.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static com.ibay.backend.service.CommonFunctions.*;
import static com.ibay.backend.service.CommonFunctions.getUserAuthoritiesString;
import static com.ibay.backend.service.ServiceParamChecks.userConversionMap;


@Service
@Profile("!test")
public class UserService {

    private final UserDao userDao;
    private final IdGenerator idGenerator;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, IdGenerator idGenerator, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.idGenerator = idGenerator;
        this.passwordEncoder = passwordEncoder;
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


    public String addUser(User user, ApplicationUserRole role) {
        if (!user.getPassword().equals(user.getConfirmPassword())) { throw new UserInvalidParametersException("Passwords do not match!");}
        if (userDao.columnContains("ibay_user", "username", user.getUsername())) { throw new UsernameTakenException(); }
        if (userDao.columnContains("ibay_user", "email", user.getEmail())) { throw new EmailTakenException(); }
        if (!user.getPassword().equals(user.getConfirmPassword())) { throw new UserInvalidParametersException("Passwords do not match!"); }
        final var id = generateUniqueID();
        if (
                userDao.insertUser(
                id,
                new Timestamp(System.currentTimeMillis()),
                user,
                passwordEncoder.encode(user.getPassword()),
                getUserAuthoritiesString(List.of(role))
                )) return id;
        return null;
    }


    public User getUserByID(String id) {
        return userDao.selectUserByID(id);
    }

    public User getUserByParam(Map<String, String> params) {
        params = ServiceParamChecks.convertRequestParams(params, userConversionMap);
        params = ServiceParamChecks.removeEmptyParams(params);
        if (ServiceParamChecks.isParamsEmpty(params)) throw new UserInvalidParametersException("No valid parameters included");
        return userDao.selectUserByParams(params);
    }

    public Boolean deleteUserByID(String id) {
        return userDao.deleteUserByID(id);
    }

    public Boolean updateUserByID(String id, User user) {


        if (!passwordEncoder.matches(user.getPassword(), getLoggedInUser().getPassword())) {
            throw new AuthorizationServiceException("Incorrect password!");
        }

        if (!user.getNewPassword().equals(user.getConfirmPassword())) {
            throw new UserInvalidParametersException("Passwords do not match!");
        }

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
