package com.ibay.backend.exceptions.auctionExceptions.handler;

import com.ibay.backend.exceptions.ExceptionBody;
import com.ibay.backend.exceptions.auctionExceptions.AuctionArgumentException;
import com.ibay.backend.exceptions.auctionExceptions.definitions.AuctionErrorDefinitions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuctionExceptionHandler {

    @ResponseBody
    @ExceptionHandler(AuctionArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ExceptionBody AuctionArgumentExceptionHandler(AuctionArgumentException exception) {
        return new ExceptionBody(AuctionErrorDefinitions.AUCTION_INVALID_ARGUMENTS, exception.getMessage());
    }

}
