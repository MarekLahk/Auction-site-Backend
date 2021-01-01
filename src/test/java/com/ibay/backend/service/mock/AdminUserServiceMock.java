package com.ibay.backend.service.mock;

import com.ibay.backend.service.AdminUserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;



@Profile("test")
@Service
public class AdminUserServiceMock {

    @Bean
    public AdminUserService adminUserService() {
        return Mockito.mock(AdminUserService.class);
    }
}
