package com.ibay.backend.exceptions.auctionExceptions;

public class AuctionInvalidParametersException extends RuntimeException{
    public AuctionInvalidParametersException() {
    }

    public AuctionInvalidParametersException(String message) {
        super(message);
    }

    public AuctionInvalidParametersException(String message, Throwable cause) {
        super(message, cause);
    }
}
