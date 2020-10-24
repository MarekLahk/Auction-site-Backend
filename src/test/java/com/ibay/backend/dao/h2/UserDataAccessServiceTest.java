package com.ibay.backend.dao.h2;

import com.ibay.backend.MocksApplication;
import com.ibay.backend.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@Sql("/TestsSqlFiles/UserDaoDBInit.sql")
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {MocksApplication.class})
@SpringBootTest
class UserDataAccessServiceTest {


    private UserDataAccessServiceH2 userDao;

    @Autowired
    UserDataAccessServiceTest(JdbcTemplate jdbcTemplate) {
        this.userDao = new UserDataAccessServiceH2(jdbcTemplate);
    }

    @Test
    void columnContains() {
        assertTrue(userDao.columnContains("ibay_user", "userid", "123456789012"));
        assertFalse(userDao.columnContains("ibay_user", "userid", "wrongID"));
    }

    @Test
    void insertUser() {
        User user = new User(null, "uniqueUsername1", "uniqueEmail1", "uniqueFullName", (Timestamp) null);
        assertTrue(userDao.insertUser("insertUserID", new Timestamp(System.currentTimeMillis()), user));
    }

    @Test
    void selectUserByID() {
        User user = new User("123456789012", "username1", "email1", "full_name1", (Timestamp) null);
        assertEquals(userDao.selectUserByID("123456789012").toTestString(), user.toTestString());
        assertNull(userDao.selectUserByID("invalidID"));
    }

    @Test
    void deleteUserByID() {
        assertTrue(userDao.deleteUserByID("123456789013"));
        assertFalse(userDao.deleteUserByID("123456789013"));
    }

    @Test
    void updateUserByID() {
        Map<String, String> params = new HashMap<>() {{
            put("FULL_NAME", "testName");
        }};
        assertTrue(userDao.updateUserByID("123456789014", params));
        assertFalse(userDao.updateUserByID("invalidID", params));
    }
}