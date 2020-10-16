package com.ibay.backend.exceptions.bidExceptions.handler;

import com.ibay.backend.exceptions.ExceptionBody;
import com.ibay.backend.exceptions.bidExceptions.AuctionEndedException;
import com.ibay.backend.exceptions.bidExceptions.BidArgumentException;
import com.ibay.backend.exceptions.bidExceptions.BidTooLowException;
import com.ibay.backend.exceptions.bidExceptions.definitions.BidErrorDefinitions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BidExceptionHandler {

    @ResponseBody
    @ExceptionHandler(AuctionEndedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ExceptionBody AuctionEndedExceptionHandler(AuctionEndedException exception) {
        return new ExceptionBody(BidErrorDefinitions.AUCTION_ENDED);
    }

    @ResponseBody
    @ExceptionHandler(BidTooLowException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ExceptionBody usernameTakenExceptionHandler(BidTooLowException exception) {
        return new ExceptionBody(BidErrorDefinitions.BID_TOO_LOW);

    }

    @ResponseBody
    @ExceptionHandler(BidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ExceptionBody BidArgumentExceptionHandler(BidArgumentException exception) {
        return new ExceptionBody(BidErrorDefinitions.BID_INVALID_ARGUMENTS, exception.getMessage());
    }
}
