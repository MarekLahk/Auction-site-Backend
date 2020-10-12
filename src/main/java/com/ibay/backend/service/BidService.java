package com.ibay.backend.service;

import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.dao.BidDao;
import com.ibay.backend.model.Auction;
import com.ibay.backend.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class BidService {

    private final BidDao bidDao;
    private final AuctionDao auctionDao;

    @Autowired
    public BidService(BidDao bidDao, AuctionDao auctionDao) {
        this.bidDao = bidDao;
        this.auctionDao = auctionDao;
    }

    private Boolean evaluateBid(Bid bid) {
        Auction auction = auctionDao.selectAuctionByID(bid.getAuctionID());
        if (auction != null) {
            if (auction.getEndTime().after(new Timestamp(System.currentTimeMillis()))) {
                Bid highestBid = bidDao.getHighestBid(bid.getAuctionID());
                if (highestBid != null) {
                    if (!highestBid.getBidOwnerID().equals(bid.getBidOwnerID())) {
                        if (highestBid.getBidAmount().compareTo(bid.getBidAmount()) < 0) {
                            System.out.println("here");
                            return true;
                        }
                    }
                } else {
                    return true;
                }
            }
        }
        System.out.println("exit");
        return false;
    }

    public UUID addBid(Bid bid) {
        if (evaluateBid(bid)) {
            final var id = UUID.randomUUID();
            if (bidDao.addBid(id, bid, new Timestamp(System.currentTimeMillis()))) return id;
        }
        return null;
    }

    public Bid a(String id) {
        return bidDao.getHighestBid(id);
    }
}
