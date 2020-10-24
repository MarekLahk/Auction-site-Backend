package com.ibay.backend.dao.h2;


import com.ibay.backend.MocksApplication;
import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.model.Auction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@Sql("/TestsSqlFiles/AuctionDaoDBInit.sql")
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {MocksApplication.class})
@SpringBootTest
public class AuctionDataAccessServiceTest {

    AuctionDao auctionDao;

    @Autowired
    public AuctionDataAccessServiceTest(JdbcTemplate jdbcTemplate) {
        auctionDao = new AuctionDataAccessServiceH2(jdbcTemplate);
    }

    @Test
    void columnContains() {
        assertTrue(auctionDao.columnContains("auctions", "auctionid", "Auction00000001"));
        assertFalse(auctionDao.columnContains("auctions", "auctionid", "FalseID"));
    }

    @Test
    void insertAuction() {
        Auction auction = new Auction(null, "title", "description", 10, "AuctionUser1", "category", null, new Timestamp(System.currentTimeMillis()));
        assertEquals("BidAuction00002", auctionDao.insertAuction("BidAuction00002", auction));
    }

    @Test
    void selectAuctionByID() {
        Auction auction = new Auction("Auction00000001", "title1", "description1", null, "AuctionUser1", "automotive", null, (Timestamp) null);
        assertEquals(auction.toTestString(), auctionDao.selectAuctionByID("Auction00000001").toTestString());
        assertNull(auctionDao.selectAuctionByID("wrongID"));
    }

}
