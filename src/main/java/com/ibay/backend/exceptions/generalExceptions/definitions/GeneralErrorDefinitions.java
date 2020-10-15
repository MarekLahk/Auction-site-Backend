package com.ibay.backend.exceptions.generalExceptions.definitions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GeneralErrorDefinitions {

    IdGeneration(
            "error/general/0001",
            "Could not generate unique id",
            "Server tried generating a unique id but failed",
            HttpStatus.INTERNAL_SERVER_ERROR)

    ;

    private final String errorCode;

    private final HttpStatus status;

    private final String userMessage;

    private final String devMessage;

    GeneralErrorDefinitions(String errorCode, String userMessage, String devMessage, HttpStatus status) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
        this.status = status;

    }
}
