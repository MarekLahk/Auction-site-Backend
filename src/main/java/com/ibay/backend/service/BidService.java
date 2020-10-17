package com.ibay.backend.service;

import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.dao.BidDao;
import com.ibay.backend.dao.UserDao;
import com.ibay.backend.exceptions.bidExceptions.BidArgumentException;
import com.ibay.backend.exceptions.bidExceptions.BidTooLowException;
import com.ibay.backend.model.Auction;
import com.ibay.backend.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.ibay.backend.service.ServiceParamChecks.*;

@Service
@Profile("!test")
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
        if (bid == null) return false;
        System.out.println(bid.getBidOwnerID());
        Boolean userExists = userDao.columnContains("ibay_user", "userid", bid.getBidOwnerID());
        System.out.println(userExists);
        if (userExists == Boolean.FALSE) throw new BidArgumentException("No such user exists");
        Auction auction = auctionDao.selectAuctionByID(bid.getAuctionID());
        if (auction == null) throw new BidArgumentException("No such auction found");
        if (auction.getEndTime().before(new Timestamp(System.currentTimeMillis()))) throw new BidArgumentException("Auction has ended");
        if (auction.getOwnerID().equals(bid.getBidOwnerID())) throw new BidArgumentException("Auction owner cannot bid on their own auction");
        Bid highestBid = bidDao.getHighestBid(bid.getAuctionID());
        if (highestBid == null) return Boolean.TRUE;
        if (highestBid.getBidOwnerID().equals(bid.getBidOwnerID())) throw new BidArgumentException("User is already the highest bidder");
        if (highestBid.getBidAmount().compareTo(bid.getBidAmount()) >= 0) throw new BidTooLowException();
        return Boolean.TRUE;
    }

    public UUID addBid(Bid bid) {
        if (evaluateBid(bid)) {
            final var id = UUID.randomUUID();
            if (bidDao.addBid(id, bid, new Timestamp(System.currentTimeMillis()))) return id;
        }
        return null;
    }

    public Bid getHighestBid(String auctionID) {
        return bidDao.getHighestBid(auctionID);
    }

    public Bid getBidByID(UUID id) {
        return bidDao.getBidByID(id);
    }

    public List<Bid> getBidByParams(Map<String, String> params) {
        System.out.println(params);
        params = convertRequestParams(params, bidConversionMap);
        params = removeEmptyParams(params);
        System.out.println(params);
        if (isParamsEmpty(params)) throw new BidArgumentException("Incorrect or no parameters");
        Integer offset = getRequestOffset(params);
        Integer limit = getRequestLimit(params, 10);
        if (!params.containsKey("bidauctionid")) throw new BidArgumentException("You need to supply an auction ID");
        return bidDao.getBidByParams(params, offset, limit);
    }
}
