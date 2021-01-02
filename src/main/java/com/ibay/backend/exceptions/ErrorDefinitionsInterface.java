package com.ibay.backend.exceptions;

import org.springframework.http.HttpStatus;

public interface ErrorDefinitionsInterface {

    public String getErrorCode();

    public HttpStatus getStatus();

    public String getUserMessage();

    public String getDevMessage();
}
