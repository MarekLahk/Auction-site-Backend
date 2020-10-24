package com.ibay.backend.exceptions.auctionExceptions.definitions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuctionErrorDefinitionsTest {



    @Test
    void getErrorCode() {
        assertEquals("error/auction/0001", AuctionErrorDefinitions.AUCTION_INVALID_ARGUMENTS.getErrorCode());
    }

    @Test
    void getStatus() {
        assertEquals(HttpStatus.BAD_REQUEST, AuctionErrorDefinitions.AUCTION_INVALID_ARGUMENTS.getStatus());
    }

    @Test
    void getUserMessage() {
        assertEquals("Invalid arguments", AuctionErrorDefinitions.AUCTION_INVALID_ARGUMENTS.getUserMessage());
    }

    @Test
    void getDevMessage() {
        assertEquals("Arguments provided are incorrect or missing", AuctionErrorDefinitions.AUCTION_INVALID_ARGUMENTS.getDevMessage());
    }
}