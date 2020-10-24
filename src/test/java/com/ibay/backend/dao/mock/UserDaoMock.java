package com.ibay.backend.dao.mock;

import com.ibay.backend.dao.UserDao;
import com.ibay.backend.dao.h2.UserDataAccessServiceH2;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("test")
@Repository
public class UserDaoMock {

    @Bean
    @Primary
    public UserDao userDao() {
        return Mockito.mock(UserDataAccessServiceH2.class);
    }
}
