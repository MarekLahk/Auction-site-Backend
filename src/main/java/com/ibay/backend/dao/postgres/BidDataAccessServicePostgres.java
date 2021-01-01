package com.ibay.backend.dao.postgres;


import com.ibay.backend.dao.BidDao;
import com.ibay.backend.exceptions.generalExceptions.IdGenerationException;
import com.ibay.backend.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Profile("production")
@Repository()
public class BidDataAccessServicePostgres implements BidDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BidDataAccessServicePostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean addBid(UUID id, Bid bid, Timestamp createTime) {
        final String sqlQuery = String.format(
                "INSERT INTO AUCTION_BIDS (BIDID, BIDAUCTIONID, BIDOWNERID, BIDAMOUNT, BIDDATETIME)" +
                " VALUES ('%s', '%s', '%s', '%s', '%s')",
                id, bid.getAuctionID(), bid.getBidOwnerID(), bid.getBidAmount(), createTime);
        try {
            return jdbcTemplate.update(sqlQuery) > 0;
        } catch (DuplicateKeyException e) {
            throw new IdGenerationException();
        }
    }

    @Override
    public Bid getBidByID(UUID id) {
        final String sqlQuery = String.format("SELECT * FROM auction_bids WHERE bidID = '%s'", id);
        return jdbcTemplate.queryForObject(sqlQuery, new Bid());
    }

    @Override
    public Bid getHighestBid(String auctionID) {
        final String sqlQuery = String.format("SELECT * FROM AUCTION_BIDS WHERE BIDAMOUNT = (SELECT MAX(BIDAMOUNT) FROM AUCTION_BIDS WHERE BIDAUCTIONID = '%s')",
                auctionID);
        try {
            return jdbcTemplate.queryForObject(sqlQuery, new Bid());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Bid> getBidByParams(Map<String, String> parameters, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM auction_bids WHERE ");
        String prefix = "";
        System.out.println(parameters);
        for (String param : parameters.keySet()) {
            sb.append(prefix).append(param).append("='").append(parameters.get(param)).append("' ");
            prefix = " and ";
        }
        sb.append("ORDER BY BIDDATETIME DESC ").append("OFFSET ").append(offset).append(" ROWS ");
        sb.append("FETCH FIRST ").append(limit).append(" ROWS ONLY");
        return jdbcTemplate.query(sb.toString(), new Bid());
    }

    @Override
    public Boolean auctionHasBid(String auctionID) {
        final String sqlQuery = String.format("SELECT EXISTS (SELECT 1 FROM auction_bids WHERE BIDAUCTIONID = '%s')", auctionID);
        return jdbcTemplate.queryForObject(sqlQuery, Boolean.class);
    }
}
