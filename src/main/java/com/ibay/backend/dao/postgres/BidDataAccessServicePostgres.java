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
}
