package com.ibay.backend.service;

import com.ibay.backend.MocksApplication;
import com.ibay.backend.dao.UserDao;
import com.ibay.backend.exceptions.generalExceptions.IdGenerationException;
import com.ibay.backend.exceptions.userExceptions.EmailTakenException;
import com.ibay.backend.exceptions.userExceptions.UsernameTakenException;
import com.ibay.backend.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;
import java.util.List;

import static com.ibay.backend.security.ApplicationUserRole.USER;
import static com.ibay.backend.service.CommonFunctions.getUserAuthoritiesString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {MocksApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class UserServiceTest {

    public UserDao userDao;
    public IdGenerator idGenerator;
    public UserService userService;
    public PasswordEncoder passwordEncoder;

    @BeforeAll
    void setUp() {
        userDao = mock(UserDao.class);
        idGenerator = mock(IdGenerator.class);
        passwordEncoder = mock(BCryptPasswordEncoder.class);
        this.userService = new UserService(userDao, idGenerator, passwordEncoder);
    }


    @Test
    void addUser() {
        User user = new User(null, "username", "email", "fullName", (Timestamp)null, "null", "null", null);
        final String id = "123456789012";
        when(idGenerator.generateStringID(12)).thenReturn(id);
        when(userDao.columnContains("ibay_user","userID", id)).thenReturn(Boolean.FALSE).thenReturn(Boolean.TRUE);
        when(userDao.columnContains("ibay_user", "username", user.getUsername())).thenReturn(Boolean.FALSE).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(userDao.columnContains("ibay_user", "email", user.getEmail())).thenReturn(Boolean.FALSE).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(userDao.insertUser(eq(id), any(Timestamp.class), eq(user), eq(null), eq(getUserAuthoritiesString(List.of(USER))))).thenReturn(Boolean.TRUE);
        assertEquals(id, userService.addUser(user, USER));
        assertThrows(UsernameTakenException.class, () -> { userService.addUser(user, USER); });
        assertThrows(EmailTakenException.class, () -> { userService.addUser(user, USER); });
        assertThrows(IdGenerationException.class, () -> { userService.addUser(user, USER); });
        when(userDao.insertUser(eq(id), any(Timestamp.class), eq(user), eq(null), eq(getUserAuthoritiesString(List.of(USER))))).thenReturn(Boolean.FALSE);
        when(userDao.columnContains("ibay_user","userID", id)).thenReturn(Boolean.FALSE);
        assertNull(userService.addUser(user, USER));
    }

    @Test
    void getUserByID() {
        User user = new User("id", "username", "email", "fullname", (Timestamp)null, null, null, null);
        when(userDao.selectUserByID(user.getId())).thenReturn(user).thenReturn(null);
        assertEquals(userService.getUserByID(user.getId()), user);
        assertNull(userService.getUserByID(user.getId()));
    }

    @Test
    void deleteUserByID() {
        String id = "id";
        when(userDao.deleteUserByID(id)).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        assertTrue(userService.deleteUserByID(id));
        assertFalse(userService.deleteUserByID(id));
    }

    @Test
    void updateUserByID() {
        //TODO Add tests.
    }



}