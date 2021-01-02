package com.ibay.backend.api.user;


import com.ibay.backend.model.Auction;
import com.ibay.backend.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static com.ibay.backend.security.CustomAnnotations.ForUsers;

@RequestMapping("/api/v1/auction")
@RestController
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @ForUsers
    @PostMapping()
    @PreAuthorize("#auction.ownerID == principal.id")
    public String addAuction(@Valid @NotNull @RequestBody Auction auction) {
        System.out.println("Here");
        return auctionService.addAuction(auction);
    }

    @GetMapping(path = "{id}")
    public Auction selectAuctionByID(@PathVariable("id") String id) {
        return auctionService.selectAuctionByID(id);
    }


    @ForUsers
    @DeleteMapping(path = "{id}")
    public Boolean deleteAuctionByID(@PathVariable("id") String id) {
        return auctionService.deleteAuctionByID(id);
    }

    @GetMapping()
    public List<Auction> getAuctionsByParameter(@RequestParam @NotBlank Map<String, String> parameters) {
        return auctionService.selectAuctionsByParameter(parameters);
    }

}
