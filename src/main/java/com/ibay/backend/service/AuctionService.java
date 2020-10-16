package com.ibay.backend.service;

import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.dao.BidDao;
import com.ibay.backend.dao.UserDao;
import com.ibay.backend.exceptions.auctionExceptions.AuctionInvalidParametersException;
import com.ibay.backend.model.Auction;
import com.ibay.backend.model.Bid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.ibay.backend.service.ServiceParamChecks.auctionConversionMap;
import static com.ibay.backend.service.ServiceParamChecks.getRequestLimit;

@Service
@Profile("!test")
public class AuctionService {

    private final AuctionDao auctionDao;
    private final BidDao bidDao;
    private final IdGenerator idGenerator;
    private final UserDao userDao;

    @Autowired
    public AuctionService(AuctionDao auctionDao, BidDao bidDao, UserDao userDao, IdGenerator idGenerator) {

        this.auctionDao = auctionDao;
        this.bidDao = bidDao;
        this.userDao = userDao;
        this.idGenerator = idGenerator;
    }

    private Boolean evaluateAuction(Auction auction) {
        if (auction == null) throw new AuctionInvalidParametersException("No auction provided");
        if (!userDao.columnContains("ibay_user", "userid", auction.getOwnerID())) throw new AuctionInvalidParametersException("No such user exists");
        if (auction.getDuration() < 1 && auction.getDuration() > 20) throw new AuctionInvalidParametersException("Invalid duration. Duration must be between 1 day and 20 days");
        if (!AuctionCategoryDefinitions.auctionCategories.contains(auction.getCategory()) || auction.getCategory().equals("all")) throw new AuctionInvalidParametersException("No such category");
        return Boolean.TRUE;
    }

    public String addAuction(Auction auction) {
        if (evaluateAuction(auction)) {
            auction.calculateEndTime();
            return auctionDao.insertAuction(RandomStringUtils.randomAlphanumeric(15), auction);
        }
        return null;
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
        parameters = ServiceParamChecks.convertRequestParams(parameters, auctionConversionMap);
        parameters = ServiceParamChecks.removeEmptyParams(parameters);
        if (ServiceParamChecks.isParamsEmpty(parameters)) throw new AuctionInvalidParametersException();
        Integer limit = getRequestLimit(parameters);
        return auctionDao.selectAuctionsByParameter(parameters, limit);

    }

    public Bid getAuctionHighestBid(String id) {
        return bidDao.getHighestBid(id);
    }

}
