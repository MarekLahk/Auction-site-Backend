package com.ibay.backend.exceptions.bidExceptions;

public class BidArgumentException extends RuntimeException{
    public BidArgumentException() {
    }

    public BidArgumentException(String message) {
        super(message);
    }

    public BidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
