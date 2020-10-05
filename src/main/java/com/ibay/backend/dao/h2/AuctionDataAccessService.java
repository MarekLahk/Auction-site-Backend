package com.ibay.backend.dao.h2;


import com.ibay.backend.dao.AuctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Profile("development")
@Repository
public class AuctionDataAccessService implements AuctionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuctionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
