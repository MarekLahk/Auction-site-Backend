package com.ibay.backend.service.mock;


import com.ibay.backend.service.ApplicationUserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class ApplicationUserServiceMock {

    @Bean
    public ApplicationUserService applicationUserService() {
        return Mockito.mock(ApplicationUserService.class);
    }
}
