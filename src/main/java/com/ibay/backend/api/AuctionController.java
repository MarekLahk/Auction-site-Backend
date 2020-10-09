package com.ibay.backend.api;


import com.ibay.backend.model.Auction;
import com.ibay.backend.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @DeleteMapping(path = "{id}")
    public Boolean deleteAuctionByID(@PathVariable("id") String id) {
        return auctionService.deleteAuctionByID(id);
    }

    @PutMapping
    public Boolean updateAuctionByID(String id, Auction auction) {
        return auctionService.updateAuctionByID(auction);
    }

    @GetMapping()
    public List<Auction> getAuctionsByParameter(@RequestParam @NotBlank LinkedHashMap<String, String> parameters) {
        return auctionService.selectAuctionsByParameter(parameters);
    }
}
