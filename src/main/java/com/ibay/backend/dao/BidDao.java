package com.ibay.backend.dao;

import com.ibay.backend.model.Bid;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BidDao {

    Boolean addBid(UUID id, Bid bid, Timestamp createTime);

    Bid getBidByID(UUID id);

    Bid getHighestBid(String auctionID);

    List<Bid> getBidByParams(Map<String, String> params, Integer offset, Integer limit);
}
