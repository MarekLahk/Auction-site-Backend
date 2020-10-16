package com.ibay.backend.dao;

import com.ibay.backend.model.Auction;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface AuctionDao {

    Boolean columnContains(String table, String columnName, String value);

    String insertAuction(String id, Auction auction);

    Auction selectAuctionByID(String id);

    Boolean deleteAuctionByID(String id);

    Boolean updateAuctionByID(Auction auction);

    List<Auction> selectAuctionsByParameter(Map<String, String> parameters, Integer limit);
}
