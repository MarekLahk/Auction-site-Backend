package com.ibay.backend.exceptions.userExceptions.definitions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserErrorDefinitions {

    /*EXAMPLE("error/bid/{unique id here}",
            "Message that may be displayed to the user",
            "Detailed error message for the developer",
            {HTTP status}
            ) */

    USERNAME_TAKEN (
            "error/user/0001",
            "Username is already taken",
            "" ,
            HttpStatus.CONFLICT
    ),

    EMAIL_TAKEN(
         "error/user/0002",
         "Email is already in use",
         "",
         HttpStatus.CONFLICT
    ),

    BAD_ARGUMENTS(
            "error/user/0003",
            "Requst includes bad arguments",
            "Request includes bad arguments",
            HttpStatus.BAD_REQUEST
    )
    ;


    private final String errorCode;

    private final HttpStatus status;

    private final String userMessage;

    private final String devMessage;

    UserErrorDefinitions(String errorCode, String userMessage, String devMessage, HttpStatus status) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
        this.status = status;

    }


}
