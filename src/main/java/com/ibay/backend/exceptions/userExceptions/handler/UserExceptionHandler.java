package com.ibay.backend.exceptions.userExceptions.handler;

import com.ibay.backend.exceptions.ExceptionBody;
import com.ibay.backend.exceptions.userExceptions.EmailTakenException;
import com.ibay.backend.exceptions.userExceptions.UserInvalidParametersException;
import com.ibay.backend.exceptions.userExceptions.UsernameTakenException;
import com.ibay.backend.exceptions.userExceptions.definitions.UserErrorDefinitions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UsernameTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ExceptionBody usernameTakenExceptionHandler(UsernameTakenException exception) {
        return new ExceptionBody(UserErrorDefinitions.USERNAME_TAKEN);

    }

    @ResponseBody
    @ExceptionHandler(EmailTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ExceptionBody emailTakenExceptionHandler(EmailTakenException exception) {
        return new ExceptionBody(UserErrorDefinitions.EMAIL_TAKEN);
    }

    @ResponseBody
    @ExceptionHandler(UserInvalidParametersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ExceptionBody userInvalidParametersExceptionHandler(UserInvalidParametersException exception) {
        return new ExceptionBody(UserErrorDefinitions.BAD_ARGUMENTS, exception.getMessage());
    }
}
