package com.ibay.backend.dao.h2;

import com.ibay.backend.MocksApplication;
import com.ibay.backend.exceptions.generalExceptions.IdGenerationException;
import com.ibay.backend.model.Bid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@Sql("/TestsSqlFiles/BidDaoDBInit.sql")
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {MocksApplication.class})
@SpringBootTest
class BidDataAccessServiceTest {

    private BidDataAccessServiceH2 bidDao;

    @Autowired
    BidDataAccessServiceTest(JdbcTemplate jdbcTemplate) {
        this.bidDao = new BidDataAccessServiceH2(jdbcTemplate);
    }

    @Test
    void addBid() {
        Bid bid = new Bid(null, "BidAuction00001", "BidUser00002", BigDecimal.valueOf(10));
        final UUID id = UUID.randomUUID();
        assertTrue(bidDao.addBid(id, bid, new Timestamp(System.currentTimeMillis())));
        assertThrows(IdGenerationException.class, () -> bidDao.addBid(id, bid, new Timestamp(System.currentTimeMillis())));
    }

    @Test
    void getBidByID() {
        Bid bid = new Bid(UUID.fromString("bd2bb7a3-a575-4fa0-8f36-7205a8209e3c"), "BidAuction00001", "BidUser00002", BigDecimal.valueOf(100.00f));
        System.out.println(bid.getBidAmount().toString());
        assertEquals(bid.toTestString(), bidDao.getBidByID(UUID.fromString("bd2bb7a3-a575-4fa0-8f36-7205a8209e3c")).toTestString());

    }

}