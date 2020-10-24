package com.ibay.backend.service;

import com.ibay.backend.MocksApplication;
import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.dao.UserDao;
import com.ibay.backend.exceptions.auctionExceptions.AuctionArgumentException;
import com.ibay.backend.model.Auction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {MocksApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class AuctionServiceTest {



    AuctionDao auctionDao;
    IdGenerator idGenerator;
    UserDao userDao;
    AuctionService auctionService;

    @BeforeAll
    void setUp() {
        auctionDao = mock(AuctionDao.class);
        idGenerator = mock(IdGenerator.class);
        userDao = mock(UserDao.class);
        auctionService = new AuctionService(auctionDao, userDao, idGenerator);

    }

    @Test
    void addAuction() {
        Auction auction = new Auction("id", "title", "description", 21, "ownerID", "automotive", null, new Timestamp(System.currentTimeMillis()));
        Auction tooShort = new Auction("id", "title", "description", 0, "ownerID", "automotive", null, new Timestamp(System.currentTimeMillis()));
        Auction wrongCategory = new Auction("id", "title", "description", 12, "ownerID", "wrongCategory", null, new Timestamp(System.currentTimeMillis()));
        Auction correct = new Auction("id","title", "description", 12, "ownerID", "automotive", null, new Timestamp(System.currentTimeMillis()));
        assertEquals("No auction provided", assertThrows(AuctionArgumentException.class, () -> auctionService.addAuction((Auction) null)).getMessage());
        when(userDao.columnContains("ibay_user", "userid", "ownerID")).thenReturn(Boolean.FALSE).thenReturn(Boolean.TRUE);
        assertEquals("No such user exists", assertThrows(AuctionArgumentException.class, () -> auctionService.addAuction(auction)).getMessage());
        assertEquals("Invalid duration. Duration must be between 1 day and 20 days", assertThrows(AuctionArgumentException.class, () -> auctionService.addAuction(auction)).getMessage());
        assertEquals("Invalid duration. Duration must be between 1 day and 20 days", assertThrows(AuctionArgumentException.class, () -> auctionService.addAuction(tooShort)).getMessage());
        when(idGenerator.generateStringID(15)).thenReturn("correctID");
        when(auctionDao.insertAuction("correctID", correct)).thenReturn("correctID");
        assertEquals("correctID", auctionService.addAuction(correct));


    }

    @Test
    void selectAuctionByID() {
        Auction auction = new Auction("id","title", "description", 5, "ownerID", "automotive", null, new Timestamp(System.currentTimeMillis()));
        when(auctionDao.selectAuctionByID("ownerID")).thenReturn(auction);
        assertEquals(auction, auctionService.selectAuctionByID("ownerID"));
    }

    @Test
    void deleteAuctionByID() {
        when(auctionDao.deleteAuctionByID("correct")).thenReturn(Boolean.TRUE);
        assertTrue(auctionService.deleteAuctionByID("correct"));
        when(auctionDao.deleteAuctionByID("incorrect")).thenReturn(Boolean.FALSE);
        assertFalse(auctionService.deleteAuctionByID("incorrect"));
    }

    @Test
    void selectAuctionsByParameter() {
        //TODO add tests
    }

}