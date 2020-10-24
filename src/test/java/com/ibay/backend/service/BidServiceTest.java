package com.ibay.backend.service;

import com.ibay.backend.MocksApplication;
import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.dao.BidDao;
import com.ibay.backend.dao.UserDao;
import com.ibay.backend.exceptions.bidExceptions.AuctionEndedException;
import com.ibay.backend.exceptions.bidExceptions.BidArgumentException;
import com.ibay.backend.exceptions.bidExceptions.BidTooLowException;
import com.ibay.backend.model.Auction;
import com.ibay.backend.model.Bid;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
@ContextConfiguration(classes = {MocksApplication.class})
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class BidServiceTest {

    public BidDao bidDao;
    public AuctionDao auctionDao;
    public UserDao userDao;
    public BidService bidService;

    private UUID id = UUID.randomUUID();
    private Bid bid = new Bid(id, "auctionID", "notOwnerID1", BigDecimal.valueOf(10));
    private Bid bidAuctionOwner = new Bid(null, "auctionID", "ownerID", BigDecimal.valueOf(2));
    private Bid bidLowerDiffOwner = new Bid(null, "auctionID", "notOwnerID2", BigDecimal.valueOf(2));
    private Bid bidSameAmountDiffOwner = new Bid(null, "auctionID", "notOwnerID2", bid.getBidAmount());
    private Bid bidSameBidder = new Bid(null, "auctionID", "notOwnerID1", BigDecimal.valueOf(5));
    private Bid bidHigherDiffOwner = new Bid(null, "auctionID", "notOwnerID2", BigDecimal.valueOf(100));
    private Auction auctionEnded = new Auction("id",null, null, null, "notOwnerID", "category", null, new Timestamp(System.currentTimeMillis() - 500000));
    private Auction auctionOngoing = new Auction("id",null, null, null, "ownerID", "category", null, new Timestamp(System.currentTimeMillis() + 500000));

    @BeforeAll
    void setUp() {
        bidDao = mock(BidDao.class);
        auctionDao = mock(AuctionDao.class);
        userDao = mock(UserDao.class);
        this.bidService = new BidService(bidDao, auctionDao, userDao);
    }
    

    @Test
    @Order(1)
    void addBidIncorrectUser() {
        assertEquals("No such user exists", assertThrows(BidArgumentException.class, () -> bidService.addBid(bid)).getMessage());
    }

    @Test
    @Order(2)
    void addBidIncorrectAuction() {
        when(userDao.columnContains(eq("ibay_user"), eq("userid"), any(String.class))).thenReturn(Boolean.TRUE);
        System.out.println(userDao.columnContains("ibay_user", "userid", bid.getBidOwnerID()));
        when(auctionDao.selectAuctionByID(any(String.class))).thenReturn(null);
        assertEquals("No such auction found", assertThrows(BidArgumentException.class, () -> bidService.addBid(bid)).getMessage());
    }

    @Test
    @Order(3)
    void addBidIncorrectAuctionEndTime() {
        when(auctionDao.selectAuctionByID(bid.getAuctionID())).thenReturn(auctionEnded).thenReturn(auctionOngoing);
        assertThrows(AuctionEndedException.class, () -> bidService.addBid(bid));
    }

    @Test
    @Order(4)
    void addBidOwnerBid() {
        assertEquals("Auction owner cannot bid on their own auction", assertThrows(BidArgumentException.class, () -> bidService.addBid(bidAuctionOwner)).getMessage());
    }

    @Test
    @Order(5)
    void addBidSameBidder() {
        when(bidDao.getHighestBid(bid.getAuctionID())).thenReturn(bid);
        assertEquals("User is already the highest bidder", assertThrows(BidArgumentException.class, () -> bidService.addBid(bidSameBidder)).getMessage());

    }

    @Test
    @Order(6)
    void addBidTooLow() {
        assertThrows(BidTooLowException.class, () -> bidService.addBid(bidLowerDiffOwner));
        assertThrows(BidTooLowException.class, () -> bidService.addBid(bidSameAmountDiffOwner));
    }

    @Test
    @Order(7)
    void addBidCouldNotAdd() {
        when(bidDao.addBid(any(UUID.class), any(Bid.class), any(Timestamp.class))).thenReturn(Boolean.FALSE).thenReturn(Boolean.TRUE);
        assertNull(bidService.addBid(bidHigherDiffOwner));
    }

    @Test
    @Order(8)
    void addBidCorrect() {
        assertEquals(UUID.class, bidService.addBid(bidHigherDiffOwner).getClass());
        when(bidDao.getHighestBid(bid.getAuctionID())).thenReturn(null);
        assertEquals(UUID.class, bidService.addBid(bidHigherDiffOwner).getClass());
    }



    @Test
    void getBidByID() {
        UUID id = UUID.randomUUID();
        when(bidDao.getBidByID(id)).thenReturn(bid).thenReturn(null);
        assertEquals(bid, bidService.getBidByID(id));
        assertNull(bidService.getBidByID(id));
    }
}