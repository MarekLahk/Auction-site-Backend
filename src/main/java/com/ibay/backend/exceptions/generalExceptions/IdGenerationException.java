package com.ibay.backend.exceptions.generalExceptions;

public class IdGenerationException extends RuntimeException {

    public IdGenerationException() {
    }

    public IdGenerationException(String message) {
        super(message);
    }

    public IdGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
