package com.ibay.backend.service.mock;



import com.ibay.backend.dao.UserDao;
import com.ibay.backend.service.UserService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
public class UserServiceMock{

    @Bean
    @Primary
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }


}
