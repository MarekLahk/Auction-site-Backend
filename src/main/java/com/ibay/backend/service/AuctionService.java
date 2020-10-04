package com.ibay.backend.service;

import com.ibay.backend.dao.AuctionDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {

    public AuctionService(@Qualifier("postgresAuction")AuctionDao auctionDao) {

    }
}
