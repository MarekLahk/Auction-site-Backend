package com.ibay.backend.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class IdGenerator {

    public IdGenerator idGenerator() {
        return new IdGenerator();
    }

    public String generateStringID(int length) {
            return RandomStringUtils.randomAlphanumeric(length);
    }
}
