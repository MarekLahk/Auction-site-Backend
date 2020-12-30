package com.ibay.backend.service;

import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.dao.UserDao;
import com.ibay.backend.exceptions.auctionExceptions.AuctionArgumentException;
import com.ibay.backend.model.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.ibay.backend.service.ServiceParamChecks.*;

@Service
@Profile("!test")
public class AuctionService {

    private final AuctionDao auctionDao;
    private final IdGenerator idGenerator;
    private final UserDao userDao;

    @Autowired
    public AuctionService(AuctionDao auctionDao, UserDao userDao, IdGenerator idGenerator) {

        this.auctionDao = auctionDao;
        this.userDao = userDao;
        this.idGenerator = idGenerator;
    }

    private Boolean evaluateAuction(Auction auction) {
        if (auction == null) throw new AuctionArgumentException("No auction provided");
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        if (!auction.getOwnerID().equals("asd")) {throw new AuthorizationServiceException("Hello");}
        if (!userDao.columnContains("ibay_user", "userid", auction.getOwnerID())) throw new AuctionArgumentException("No such user exists");
        if (auction.getDuration() < 1 || auction.getDuration() > 20) throw new AuctionArgumentException("Invalid duration. Duration must be between 1 day and 20 days");
        if (!AuctionCategoryDefinitions.auctionCategories.contains(auction.getCategory()) || auction.getCategory().equals("all")) throw new AuctionArgumentException("No such category");
        return Boolean.TRUE;
    }

    public String addAuction(Auction auction) {
        if (evaluateAuction(auction)) {
            auction.calculateEndTime();
            return auctionDao.insertAuction(idGenerator.generateStringID(15), auction);
        }
        return null;
    }

    public Auction selectAuctionByID(String id) {
        return auctionDao.selectAuctionByID(id);
    }

    public Boolean deleteAuctionByID(String id) {
        return auctionDao.deleteAuctionByID(id);
    }


    public List<Auction> selectAuctionsByParameter(Map<String, String> parameters) {
        parameters = ServiceParamChecks.convertRequestParams(parameters, auctionConversionMap);
        parameters = ServiceParamChecks.removeEmptyParams(parameters);
        if (ServiceParamChecks.isParamsEmpty(parameters)) throw new AuctionArgumentException();
        Integer limit = getRequestLimit(parameters);
        Integer offset = getRequestOffset(parameters);
        if (parameters.containsKey("category") && parameters.get("category").equals("all")) parameters.remove("category");
        return auctionDao.selectAuctionsByParameter(parameters, limit, offset);

    }

}
