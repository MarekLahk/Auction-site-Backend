package com.ibay.backend.dao.postgres;

import com.ibay.backend.dao.AuctionDao;
import com.ibay.backend.model.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Profile("production")
@Repository()
public class AuctionDataAccessServicePostgres implements AuctionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuctionDataAccessServicePostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean columnContains(String table, String columnName, String value) {
        final String sqlQuery = String.format(
                "SELECT COUNT(%s) as Count FROM %s WHERE %s = '%s'"
                , columnName, table, columnName, value);

        return jdbcTemplate.query(sqlQuery, (resultsSet, i) -> resultsSet.getInt("count")).get(0) > 0;
    }

    @Override
    public String insertAuction(String id,  Auction auction) {
        final String sqlQuery = String.format("INSERT INTO auctions" +
                        " (auctionID, auctionOwner, title, description, createTime, endDateTime)" +
                        " VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                id, auction.getOwnerID(), auction.getTitle(), auction.getDescription(),
                new Timestamp(System.currentTimeMillis()), auction.getEndTime());
        jdbcTemplate.update(sqlQuery);
        return id;
    }

    @Override
    public Auction selectAuctionByID(String id) {
        final String sqlQuery = String.format("SELECT * FROM auctions WHERE auctionID = '%s'", id);
        return jdbcTemplate.queryForObject(sqlQuery, new Auction());

    }

    @Override
    public Boolean deleteAuctionByID(String id) {
        final String sqlQuery = String.format("DELETE FROM auctions WHERE auctionID = '%s'", id);
        return jdbcTemplate.update(sqlQuery) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean updateAuctionByID(Auction auction) {
        //TODO Add update functionality
        return null;
    }

    @Override
    public List<Auction> selectAuctionsByParameter(Map<String, String> parameters) {
        Map.Entry<String, String> param = parameters.entrySet().iterator().next();
        System.out.println(param);
        final String sqlQuery = String.format("SELECT * FROM auctions WHERE %s = '%s'", param.getKey(), param.getValue());
        return jdbcTemplate.query(sqlQuery, new Auction());
    }
}
