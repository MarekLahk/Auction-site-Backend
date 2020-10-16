package com.ibay.backend.exceptions.userExceptions;

public class UserInvalidParametersException extends RuntimeException{
    public UserInvalidParametersException() {
    }

    public UserInvalidParametersException(String message) {
        super(message);
    }

    public UserInvalidParametersException(String message, Throwable cause) {
        super(message, cause);
    }
}
