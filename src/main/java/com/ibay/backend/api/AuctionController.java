package com.ibay.backend.api;


import com.ibay.backend.model.Auction;
import com.ibay.backend.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("/api/v1/auction")
@RestController
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping()
    public String addAuction(@Valid @NotNull @RequestBody Auction auction) {
        return auctionService.addAuction(auction);
    }

    @GetMapping(path = "{id}")
    public Auction selectAuctionByID(@PathVariable("id") String id) {
        return auctionService.selectAuctionByID(id);
    }

    public Boolean deleteAuctionByID(String id) {
        return null;
    }

    public Boolean updateAuctionByID(String id, Auction auction) {
        return null;
    }
}
