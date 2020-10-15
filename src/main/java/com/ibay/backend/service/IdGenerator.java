package com.ibay.backend.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class IdGenerator {

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }

    public String generateStringID(int length) {
        if (length > 0) {
            return RandomStringUtils.randomAlphanumeric(length);
        } else {
            return null;
        }
    }
}
