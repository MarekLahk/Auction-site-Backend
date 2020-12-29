package com.ibay.backend.api.admin;

import com.ibay.backend.service.AdminAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @DeleteMapping(path = "{id}")
    public void DeleteAuctionByID(@PathVariable("id") String id) {
        adminAuctionService.deleteAuctionByID(id);
    }


}
