package com.ibay.backend.dao;

import com.ibay.backend.model.Auction;

import java.sql.Timestamp;

public interface AuctionDao {

    Boolean columnContains(String table, String columnName, String value);

    String insertAuction(String id, Timestamp endTimeDate , Auction auction);

    Auction selectAuctionByID(String id);

    Boolean deleteAuctionByID(String id);

    Boolean updateAuctionByID(String id, Auction auction);
}
