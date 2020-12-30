package com.ibay.backend.api.admin;

import com.ibay.backend.model.Auction;
import com.ibay.backend.service.AdminAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Auction> getAllAuctions() {
        return adminAuctionService.getAllAuctions();
    }


}
