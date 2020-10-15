package com.ibay.backend.dao.mock;

import com.ibay.backend.dao.AuctionDao;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("test")
@Repository
public class AuctionDaoMock {

    @Bean
    @Primary
    public AuctionDao auctionDao() {
        return Mockito.mock(AuctionDao.class);
    };
}
