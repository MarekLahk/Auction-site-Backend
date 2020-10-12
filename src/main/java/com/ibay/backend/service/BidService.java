package com.ibay.backend.service;

import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.dao.BidDao;
import com.ibay.backend.dao.UserDao;
import com.ibay.backend.exceptions.pidExceptions.BidArgumentException;
import com.ibay.backend.exceptions.pidExceptions.BidTooLowException;
import com.ibay.backend.model.Auction;
import com.ibay.backend.model.Bid;
import com.ibay.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class BidService {

    private final BidDao bidDao;
    private final AuctionDao auctionDao;
    private final UserDao userDao;

    @Autowired
    public BidService(BidDao bidDao, AuctionDao auctionDao, UserDao userDao) {
        this.bidDao = bidDao;
        this.auctionDao = auctionDao;
        this.userDao = userDao;
    }

    private Boolean evaluateBid(Bid bid) {
        Auction auction = auctionDao.selectAuctionByID(bid.getAuctionID());
        User user = userDao.selectUserByID(bid.getBidOwnerID());
        if (user == null) {
            throw new BidArgumentException("No such user exists");
        }
        if (auction != null) {
            if (auction.getEndTime().after(new Timestamp(System.currentTimeMillis()))) {
                Bid highestBid = bidDao.getHighestBid(bid.getAuctionID());
                if (highestBid != null) {
                    if (!highestBid.getBidOwnerID().equals(bid.getBidOwnerID())) {
                        if (highestBid.getBidAmount().compareTo(bid.getBidAmount()) < 0) {
                            System.out.println("here");
                            return true;
                        } else {
                            throw new BidTooLowException();
                        }
                    } else {
                        throw new BidArgumentException("User is already the highest bidder");
                    }
                } else {
                    return true;
                }
            } else {
                throw new BidArgumentException("Auction has ended");
            }
        } else {
            throw new BidArgumentException("No such auction found");
        }
    }

    public UUID addBid(Bid bid) {
        if (evaluateBid(bid)) {
            final var id = UUID.randomUUID();
            if (bidDao.addBid(id, bid, new Timestamp(System.currentTimeMillis()))) return id;
        } else {
            throw new BidArgumentException();
        }
        return null;
    }

    public Bid getHighestBid(String auctionID) {
        return bidDao.getHighestBid(auctionID);
    }

    public Bid getBidByID(UUID id) {
        return bidDao.getBidByID(id);
    }
}
