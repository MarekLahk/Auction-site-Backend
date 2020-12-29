package com.ibay.backend.service;


import com.ibay.backend.dao.AuctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class AdminAuctionService {

    private final AuctionDao auctionDao;

    @Autowired
    public AdminAuctionService(AuctionDao auctionDao) {
        this.auctionDao = auctionDao;
    }

    public void deleteAuctionByID(String id) {
        auctionDao.deleteAuctionByID(id);
    }
}
