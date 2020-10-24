package com.ibay.backend.exceptions.auctionExceptions.definitions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuctionErrorDefinitions {

    AUCTION_INVALID_ARGUMENTS(
            "error/auction/0001",
            "Invalid arguments",
            "Arguments provided are incorrect or missing",
            HttpStatus.BAD_REQUEST
    )

    ;

    private final String errorCode;

    private final HttpStatus status;

    private final String userMessage;

    private final String devMessage;

    AuctionErrorDefinitions(String errorCode, String userMessage, String devMessage, HttpStatus status) {
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
        this.status = status;

    }
}
