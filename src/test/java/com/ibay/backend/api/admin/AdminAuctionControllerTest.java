package com.ibay.backend.api.admin;

import com.ibay.backend.MocksApplication;
import com.ibay.backend.service.AdminAuctionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {MocksApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class AdminAuctionControllerTest {

    private final AdminAuctionService adminAuctionService;
    private final AdminAuctionController adminAuctionController;

    @Autowired
    public AdminAuctionControllerTest(AdminAuctionService adminAuctionService) {
        this.adminAuctionService = adminAuctionService;
        this.adminAuctionController = new AdminAuctionController(adminAuctionService);
    }

    @Test
    void getAllAuctions() {
        when(adminAuctionService.getAllAuctions()).thenReturn(new ArrayList<>());
        assertEquals(adminAuctionController.getAllAuctions(), new ArrayList<>());
    }
}