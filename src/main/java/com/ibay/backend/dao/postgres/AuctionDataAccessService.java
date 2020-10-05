package com.ibay.backend.dao.postgres;

import com.ibay.backend.dao.AuctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Profile("production")
@Repository()
public class AuctionDataAccessService implements AuctionDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuctionDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        System.out.println("works");
    }
}
