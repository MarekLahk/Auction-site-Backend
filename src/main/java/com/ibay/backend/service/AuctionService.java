package com.ibay.backend.service;

import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.model.Auction;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuctionService {

    private final AuctionDao auctionDao;

    @Autowired
    public AuctionService(AuctionDao auctionDao) {

        this.auctionDao = auctionDao;

    }

    public String addAuction(Auction auction) {
        return auctionDao.insertAuction(RandomStringUtils.randomAlphanumeric(15),
                new Timestamp(System.currentTimeMillis()), auction);
    }

    public Auction selectAuctionByID(String id) {
        return auctionDao.selectAuctionByID(id);
    }

    public Boolean deleteAuctionByID(String id) {
        return null;
    }

    public Boolean updateAuctionByID(String id, Auction auction) {
        return null;
    }
}
