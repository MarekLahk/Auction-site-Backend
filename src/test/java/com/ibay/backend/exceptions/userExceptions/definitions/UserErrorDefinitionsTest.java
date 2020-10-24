package com.ibay.backend.exceptions.userExceptions.definitions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserErrorDefinitionsTest {

    @Test
    void getErrorCode() {
        assertEquals("error/user/0001", UserErrorDefinitions.USERNAME_TAKEN.getErrorCode());
    }

    @Test
    void getStatus() {
        assertEquals(HttpStatus.CONFLICT, UserErrorDefinitions.USERNAME_TAKEN.getStatus());

    }

    @Test
    void getUserMessage() {
        assertEquals("Username is already taken", UserErrorDefinitions.USERNAME_TAKEN.getUserMessage());

    }

    @Test
    void getDevMessage() {
        assertEquals("", UserErrorDefinitions.USERNAME_TAKEN.getDevMessage());

    }
}