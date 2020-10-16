package com.ibay.backend.exceptions.generalExceptions.handler;

import com.ibay.backend.exceptions.ExceptionBody;
import com.ibay.backend.exceptions.generalExceptions.IdGenerationException;
import com.ibay.backend.exceptions.generalExceptions.definitions.GeneralErrorDefinitions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GeneralExceptionHandler {

    @ResponseBody
    @ExceptionHandler(IdGenerationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ExceptionBody IdGenerationExceptionHandler(IdGenerationException exception) {
        return new ExceptionBody(GeneralErrorDefinitions.IdGeneration);
    }

}
