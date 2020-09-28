package com.ibay.backend.exceptions.userExceptions;

public class EmailTakenException extends RuntimeException {

    public EmailTakenException() {
    }

    public EmailTakenException(String message) {
        super(message);
    }

    public EmailTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
