package com.ibay.backend.service.mock;


import com.ibay.backend.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
public class UserServiceMock{

    @Bean
    @Primary
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }


}
