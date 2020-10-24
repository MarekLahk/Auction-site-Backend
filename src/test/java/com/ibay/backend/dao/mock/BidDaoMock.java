package com.ibay.backend.dao.mock;


import com.ibay.backend.dao.BidDao;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("test")
@Repository
public class BidDaoMock {

    @Bean
    @Primary
    public BidDao bidDao() {
        return Mockito.mock(BidDao.class);
    }
}
