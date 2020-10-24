package com.ibay.backend.exceptions.bidExceptions.definitions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BidErrorDefinitionsTest {

    @Test
    void getErrorCode() {
        assertEquals("error/bid/0001", BidErrorDefinitions.AUCTION_ENDED.getErrorCode());
    }

    @Test
    void getStatus() {
        assertEquals(HttpStatus.FORBIDDEN, BidErrorDefinitions.AUCTION_ENDED.getStatus());

    }

    @Test
    void getUserMessage() {
        assertEquals("Auction has ended.", BidErrorDefinitions.AUCTION_ENDED.getUserMessage());

    }

    @Test
    void getDevMessage() {
        assertEquals("Bids cannot be made to already ended auctions", BidErrorDefinitions.AUCTION_ENDED.getDevMessage());

    }
}