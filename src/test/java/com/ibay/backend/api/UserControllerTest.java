package com.ibay.backend.api;

import com.ibay.backend.MocksApplication;
import com.ibay.backend.model.User;
import com.ibay.backend.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {MocksApplication.class})
@SpringBootTest
public class UserControllerTest {


    @Autowired
    public UserController userController;

    @Autowired
    public UserService userService;


    private User user = new User("id", "username", "email", "name", (Timestamp)null);

    @Test
    void addUserReturnUser() {
        when(userController.addUser(user)).thenReturn(user.getId());
        assertEquals(user.getId(),userService.addUser(user));
    }

    @Test
    void getUserByID() {
        when(userController.getUserByID("id")).thenReturn(user);
        assertEquals(user, userService.getUserByID(user.getId()));
    }

    @Test
    void deleteUserByID() {
        when(userController.deleteUserByID("id")).thenReturn(Boolean.TRUE);
        when(userController.deleteUserByID("wrongID")).thenReturn(Boolean.FALSE);
        assertEquals(userService.deleteUserByID("id"), Boolean.TRUE);
        assertEquals(userService.deleteUserByID("wrongID"), Boolean.FALSE);
    }

    @Test
    void updateUserByID() {
        final User updateUser = new User(null, "New username", "New email", "New name", (Timestamp) null);
        when(userController.updateUserByID("id", updateUser)).thenReturn(Boolean.TRUE);
        when(userController.updateUserByID("wrongID", updateUser)).thenReturn(Boolean.FALSE);
        assertEquals(userService.updateUserByID("id", updateUser), Boolean.TRUE);
        assertEquals(userService.updateUserByID("wrongID", updateUser), Boolean.FALSE);
    }
}