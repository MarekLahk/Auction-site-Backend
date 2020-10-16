package com.ibay.backend.exceptions.bidExceptions;

public class BidTooLowException extends RuntimeException {

    public BidTooLowException() {
    }

    public BidTooLowException(String message) {
        super(message);
    }

    public BidTooLowException(String message, Throwable cause) {
        super(message, cause);
    }
}
