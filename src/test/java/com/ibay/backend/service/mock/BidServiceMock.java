package com.ibay.backend.service.mock;



import com.ibay.backend.service.BidService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class BidServiceMock {

    @Bean
    @Primary
    public BidService bidService() {
        return Mockito.mock(BidService.class);
    }
}
