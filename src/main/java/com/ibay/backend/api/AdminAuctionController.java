package com.ibay.backend.api;


import com.ibay.backend.service.AdminAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin/api/v1/auction")
@RestController
public class AdminAuctionController {

    private final AdminAuctionService adminAuctionService;

    @Autowired
    public AdminAuctionController(AdminAuctionService adminAuctionService) {
        this.adminAuctionService = adminAuctionService;
    }


}
