package com.ibay.backend.api;

import com.ibay.backend.MocksApplication;
import com.ibay.backend.api.user.BidController;
import com.ibay.backend.model.Bid;
import com.ibay.backend.service.BidService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {MocksApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class BidControllerTest {


    private final BidController bidController;
    private final BidService bidService;

    private Bid bid = new Bid(UUID.randomUUID(), "auctionID", "ownerID", BigDecimal.valueOf(10));

    @Autowired
    public BidControllerTest(BidService bidService) {
        this.bidService = bidService;
        this.bidController = new BidController(bidService);
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

    @Test
    void getBidByParam() {
        Bid bid = new Bid(UUID.randomUUID(), "auctionID", "ownerID", BigDecimal.valueOf(10));
        Map<String, String> params = new HashMap<>();
        params.put("ownerID", "ownerID");
        List<Bid> bidList = new LinkedList<>();
        bidList.add(bid);
        when(bidService.getBidByParams(params)).thenReturn(bidList);
        assertEquals(bidList, bidController.getBidByParam(params));

    }
}