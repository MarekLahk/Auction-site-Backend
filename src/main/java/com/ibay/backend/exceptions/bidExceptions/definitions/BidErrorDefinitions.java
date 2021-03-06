package com.ibay.backend.exceptions.bidExceptions.definitions;

import com.ibay.backend.exceptions.ErrorDefinitionsInterface;
import org.springframework.http.HttpStatus;

public enum BidErrorDefinitions implements ErrorDefinitionsInterface {

    /*EXAMPLE("error/bid/{unique id here}",
            "Message that may be displayed to the user",
            "Detailed error message for the developer",
            {HTTP status}
            ) */

    AUCTION_ENDED(
            "error/bid/0001",
            "Auction has ended.",
            "Bids cannot be made to already ended auctions",
            HttpStatus.FORBIDDEN
    ),

    BID_TOO_LOW(
            "error/bid/0002",
            "Current highest bid is higher then your bid",
            "New bid must be higher then the current highest bid",
            HttpStatus.CONFLICT
    ),

    BID_INVALID_ARGUMENTS(
            "error/bid/0003",
            "Something went wrong with your bid",
            "Request includes invalid data",
            HttpStatus.BAD_REQUEST
    )

    ;

    private final String errorCode;
    private final HttpStatus status;
    private final String userMessage;
    private final String devMessage;

    BidErrorDefinitions(String errorCode, String userMessage, String devMessage, HttpStatus status) {
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
