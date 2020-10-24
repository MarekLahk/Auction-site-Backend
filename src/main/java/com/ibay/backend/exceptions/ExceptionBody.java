package com.ibay.backend.exceptions;

import com.ibay.backend.exceptions.auctionExceptions.definitions.AuctionErrorDefinitions;
import com.ibay.backend.exceptions.generalExceptions.definitions.GeneralErrorDefinitions;
import com.ibay.backend.exceptions.bidExceptions.definitions.BidErrorDefinitions;
import com.ibay.backend.exceptions.userExceptions.definitions.UserErrorDefinitions;
import lombok.Getter;

public class ExceptionBody{

    @Getter private final String errorCode;
    @Getter private final String userMessage;
    @Getter private final String devMessage;
    @Getter private final int status;


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

    public ExceptionBody(BidErrorDefinitions definition, String message) {
        this.errorCode = definition.getErrorCode();
        if (message != null && !message.strip().equals("")) {
            this.userMessage = message;
        } else {
            this.userMessage = definition.getUserMessage();
        }
        this.devMessage = definition.getDevMessage();
        this.status = definition.getStatus().value();
    }

    public ExceptionBody(UserErrorDefinitions definition, String message) {
        this.errorCode = definition.getErrorCode();
        if (message != null && !message.strip().equals("")) {
            this.userMessage = message;
        } else {
            this.userMessage = definition.getUserMessage();
        }
        this.devMessage = definition.getDevMessage();
        this.status = definition.getStatus().value();
    }

    public ExceptionBody(AuctionErrorDefinitions definition, String message) {
        this.errorCode = definition.getErrorCode();
        if (message != null && !message.strip().equals("")) {
            this.userMessage = message;
        } else {
            this.userMessage = definition.getUserMessage();
        }
        this.devMessage = definition.getDevMessage();
        this.status = definition.getStatus().value();
    }

    public ExceptionBody(GeneralErrorDefinitions definition) {
        this.errorCode = definition.getErrorCode();
        this.userMessage = definition.getUserMessage();
        this.devMessage = definition.getDevMessage();
        this.status = definition.getStatus().value();
    }

}
