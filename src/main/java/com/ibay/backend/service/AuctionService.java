package com.ibay.backend.service;

import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.model.Auction;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class AuctionService {

    private final AuctionDao auctionDao;

    @Autowired
    public AuctionService(AuctionDao auctionDao) {

        this.auctionDao = auctionDao;

    }

    public String addAuction(Auction auction) {
        auction.calculateEndTime();
        return auctionDao.insertAuction(RandomStringUtils.randomAlphanumeric(15), auction);
    }

    public Auction selectAuctionByID(String id) {
        return auctionDao.selectAuctionByID(id);
    }

    public Boolean deleteAuctionByID(String id) {
        return auctionDao.deleteAuctionByID(id);
    }

    public Boolean updateAuctionByID(Auction auction) {
        return auctionDao.updateAuctionByID(auction);
    }

    public List<Auction> selectAuctionsByParameter(Map<String, String> parameters) {
        parameters = ServiceParamChecks.convertAuctionRequestParams(parameters);
        parameters = ServiceParamChecks.removeEmptyParams(parameters);
        if (ServiceParamChecks.isParamsEmpty(parameters)) return null; //TODO Add Exception
        return auctionDao.selectAuctionsByParameter(parameters);
        //return auctionDao.selectAuctionsByParameter(parameters);
    }

}
