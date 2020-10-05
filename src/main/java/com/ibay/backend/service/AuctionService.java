package com.ibay.backend.service;

import com.ibay.backend.dao.AuctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {

    private final AuctionDao auctionDao;

    @Autowired
    public AuctionService(AuctionDao auctionDao) {

        this.auctionDao = auctionDao;

    }
}
