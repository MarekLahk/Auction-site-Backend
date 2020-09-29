package com.ibay.backend.exceptions.userExceptions.definitions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserErrorDefinitions {



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
    );


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
