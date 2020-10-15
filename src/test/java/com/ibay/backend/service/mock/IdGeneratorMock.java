package com.ibay.backend.service.mock;

import com.ibay.backend.service.IdGenerator;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class IdGeneratorMock {

    @Primary
    @Bean
    public IdGenerator idGenerator(){
        return Mockito.mock(IdGenerator.class);
    }

}
