package com.ibay.backend.exceptions.generalExceptions.definitions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneralErrorDefinitionsTest {

    @Test
    void getErrorCode() {
        assertEquals("error/general/0001", GeneralErrorDefinitions.IdGeneration.getErrorCode());
    }

    @Test
    void getStatus() {
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, GeneralErrorDefinitions.IdGeneration.getStatus());

    }

    @Test
    void getUserMessage() {
        assertEquals("Could not generate unique id", GeneralErrorDefinitions.IdGeneration.getUserMessage());

    }

    @Test
    void getDevMessage() {
        assertEquals("Server tried generating a unique id but failed", GeneralErrorDefinitions.IdGeneration.getDevMessage());

    }
}