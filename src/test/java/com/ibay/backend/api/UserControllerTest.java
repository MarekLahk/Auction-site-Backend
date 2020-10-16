package com.ibay.backend.api;

import com.ibay.backend.MocksApplication;
import com.ibay.backend.model.User;
import com.ibay.backend.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserControllerTest {


    public UserController userController;
    public UserService userService;


    private User user = new User("id", "username", "email", "name", (Timestamp)null);

    @BeforeAll
    void setUp() {

        userService = mock(UserService.class);

        userController = new UserController(userService);
    }

    @Test
    void addUserReturnUser() {
        when(userService.addUser(user)).thenReturn(user.getId());
        assertEquals(user.getId(),userController.addUser(user));
    }

    @Test
    void getUserByID() {
        when(userService.getUserByID("id")).thenReturn(user);
        assertEquals(user, userController.getUserByID(user.getId()));
    }

    @Test
    void deleteUserByID() {
        when(userService.deleteUserByID("id")).thenReturn(Boolean.TRUE);
        when(userService.deleteUserByID("wrongID")).thenReturn(Boolean.FALSE);
        assertEquals(userController.deleteUserByID("id"), Boolean.TRUE);
        assertEquals(userController.deleteUserByID("wrongID"), Boolean.FALSE);
    }

    @Test
    void updateUserByID() {
        final User updateUser = new User(null, "New username", "New email", "New name", (Timestamp) null);
        when(userService.updateUserByID("id", updateUser)).thenReturn(Boolean.TRUE);
        when(userService.updateUserByID("wrongID", updateUser)).thenReturn(Boolean.FALSE);
        assertEquals(userController.updateUserByID("id", updateUser), Boolean.TRUE);
        assertEquals(userController.updateUserByID("wrongID", updateUser), Boolean.FALSE);
    }
}