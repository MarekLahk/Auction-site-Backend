package com.ibay.backend.service;

import com.ibay.backend.MocksApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@ContextConfiguration(classes = {MocksApplication.class})
@SpringBootTest
class IdGeneratorTest {

    @Autowired
    IdGenerator idGenerator;

    @Test
    void idGenerator() {
        assertEquals(IdGenerator.class, idGenerator.idGenerator().getClass());
    }

    @Test
    void generateStringID() {
        assertEquals(10, idGenerator.generateStringID(10).length());
    }
}