package com.ibay.backend.exceptions.pidExceptions;

public class AuctionEndedException extends RuntimeException{

    public AuctionEndedException() {
    }

    public AuctionEndedException(String message) {
        super(message);
    }

    public AuctionEndedException(String message, Throwable cause) {
        super(message, cause);
    }
}
