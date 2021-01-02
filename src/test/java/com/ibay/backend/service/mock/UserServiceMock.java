package com.ibay.backend.service.mock;


import com.ibay.backend.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class UserServiceMock{

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }


}
