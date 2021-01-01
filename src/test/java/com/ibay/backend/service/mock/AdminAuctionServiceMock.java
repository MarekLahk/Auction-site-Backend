package com.ibay.backend.service.mock;

import com.ibay.backend.service.AdminAuctionService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class AdminAuctionServiceMock {

    @Bean
    public AdminAuctionService adminAuctionService() {
        return Mockito.mock(AdminAuctionService.class);
    }
}
