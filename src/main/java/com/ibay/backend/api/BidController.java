package com.ibay.backend.api;

import com.ibay.backend.model.Bid;
import com.ibay.backend.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/bid")
@RestController
public class BidController {

    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping()
    public UUID addBid(@RequestBody Bid bid) {
        return bidService.addBid(bid);
    }

    @GetMapping(path = "{id}")
    public Bid getBidByID(@PathVariable UUID id) {
        return bidService.getBidByID(id);
    }

    @GetMapping
    public List<Bid> getBidByParam(@RequestParam @NotNull LinkedHashMap<String, String> params) {
        return bidService.getBidByParams(params);
    }

}
