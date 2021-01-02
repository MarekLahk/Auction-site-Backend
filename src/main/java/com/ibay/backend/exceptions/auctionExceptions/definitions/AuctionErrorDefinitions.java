package com.ibay.backend.exceptions.auctionExceptions.definitions;

import com.ibay.backend.exceptions.ErrorDefinitionsInterface;
import org.springframework.http.HttpStatus;

public enum AuctionErrorDefinitions implements ErrorDefinitionsInterface {

    AUCTION_INVALID_ARGUMENTS(
            "error/auction/0001",
            "Invalid arguments",
            "Arguments provided are incorrect or missing",
            HttpStatus.BAD_REQUEST
    ),

    AUCTION_HAS_BIDS(
            "error/auction/0002",
            "Cannot delete auction with bids",
            "Trying to delete auction that has bids",
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

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getUserMessage() {
        return this.userMessage;
    }

    @Override
    public String getDevMessage() {
        return this.devMessage;
    }
}
