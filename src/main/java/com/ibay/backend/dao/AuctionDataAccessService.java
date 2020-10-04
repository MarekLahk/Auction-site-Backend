package com.ibay.backend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("postgresAuction")
public class AuctionDataAccessService implements AuctionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuctionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
