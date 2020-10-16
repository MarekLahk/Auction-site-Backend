package com.ibay.backend.api;

import com.ibay.backend.MocksApplication;
import com.ibay.backend.model.Bid;
import com.ibay.backend.service.AuctionService;
import com.ibay.backend.service.BidService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {MocksApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class BidControllerTest {


    public BidController bidController;
    public BidService bidService;

    private Bid bid = new Bid(UUID.randomUUID(), "auctionID", "ownerID", BigDecimal.valueOf(10));

    @BeforeAll
    void setUp() {
        bidService = mock(BidService.class);
        bidController = new BidController(bidService);
    }

    @Test
    void addBid() {
        when(bidService.addBid(bid)).thenReturn(bid.getBidID());
        assertEquals(bidController.addBid(bid), bid.getBidID());
    }

    @Test
    void getBidByID() {
        when(bidService.getBidByID(bid.getBidID())).thenReturn(bid);
        final UUID uuid = UUID.randomUUID();
        when(bidService.getBidByID(uuid)).thenReturn(null);
        assertEquals(bid, bidController.getBidByID(bid.getBidID()));
        assertNull(bidController.getBidByID(uuid));

    }
}