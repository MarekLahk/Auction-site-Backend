package com.ibay.backend.exceptions;

import com.ibay.backend.exceptions.pidExceptions.definitions.BidErrorDefinitions;
import com.ibay.backend.exceptions.userExceptions.definitions.UserErrorDefinitions;
import lombok.Getter;

public class ExceptionBody{

    @Getter private final String errorCode;
    @Getter private final String userMessage;
    @Getter private final String devMessage;
    @Getter private final int status;

    public ExceptionBody(String errorCode, String userMessage, String devMessage, int status) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
        this.status = status;
    }



    public ExceptionBody(UserErrorDefinitions definition) {
        this.errorCode = definition.getErrorCode();
        this.userMessage = definition.getUserMessage();
        this.devMessage = definition.getDevMessage();
        this.status = definition.getStatus().value();
    }


    public ExceptionBody(BidErrorDefinitions definition) {
        this.errorCode = definition.getErrorCode();
        this.userMessage = definition.getUserMessage();
        this.devMessage = definition.getDevMessage();
        this.status = definition.getStatus().value();
    }
}
