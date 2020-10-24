package com.ibay.backend.api;


import com.ibay.backend.MocksApplication;
import com.ibay.backend.model.Auction;
import com.ibay.backend.service.AuctionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {MocksApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class AuctionControllerTest {

    private AuctionService auctionService;
    public AuctionController auctionController;

    private Auction auction = new Auction("id", "title", "description", 10, "ownerID", "automotive", null, new Timestamp(System.currentTimeMillis()));


    @BeforeAll
    void setUp() {
        auctionService = mock(AuctionService.class);
        auctionController = new AuctionController(auctionService);
    }

    @Test
    void addAuction() {
        when(auctionService.addAuction(auction)).thenReturn("auctionID");
        assertEquals("auctionID", auctionController.addAuction(auction));
    }

    @Test
    void selectAuctionByID() {
        when(auctionService.selectAuctionByID("auctionID")).thenReturn(auction);
        assertEquals(auction, auctionController.selectAuctionByID("auctionID"));
    }

    @Test
    void deleteAuctionByID() {
        when(auctionService.deleteAuctionByID("auctionID")).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        assertTrue(auctionController.deleteAuctionByID("auctionID"));
        assertFalse(auctionController.deleteAuctionByID("auctionID"));
    }

    @Test
    void getAuctionsByParameter() {
        Map<String, String> params = Map.ofEntries(
                Map.entry("category", "automotive")
        );
        List<Auction> output = new ArrayList<>();
        output.add(auction);
        when(auctionService.selectAuctionsByParameter(params)).thenReturn(output);
        assertEquals(output, auctionController.getAuctionsByParameter(params));
    }
}
