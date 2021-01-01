package com.ibay.backend.dao.mock;


import com.ibay.backend.dao.AuthDao;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("test")
@Repository
public class AuthDaoMock {

    @Bean
    public AuthDao authDao() {
        return Mockito.mock(AuthDao.class);
    }
}
