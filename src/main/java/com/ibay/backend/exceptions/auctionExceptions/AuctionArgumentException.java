package com.ibay.backend.exceptions.auctionExceptions;

public class AuctionArgumentException extends RuntimeException{
    public AuctionArgumentException() {
    }

    public AuctionArgumentException(String message) {
        super(message);
    }

    public AuctionArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
