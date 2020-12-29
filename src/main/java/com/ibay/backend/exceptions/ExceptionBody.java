package com.ibay.backend.exceptions;

import lombok.Getter;

public class ExceptionBody{

    @Getter private final String errorCode;
    @Getter private final String userMessage;
    @Getter private final String devMessage;
    @Getter private final int status;


    public ExceptionBody(ErrorDefinitionsInterface definition) {
        this.errorCode = definition.getErrorCode();
        this.userMessage = definition.getUserMessage();
        this.devMessage = definition.getDevMessage();
        this.status = definition.getStatus().value();
    }


    public ExceptionBody(ErrorDefinitionsInterface definition, String message) {
        this.errorCode = definition.getErrorCode();
        if (message != null && !message.strip().equals("")) {
            this.userMessage = message;
        } else {
            this.userMessage = definition.getUserMessage();
        }
        this.devMessage = definition.getDevMessage();
        this.status = definition.getStatus().value();
    }



}
