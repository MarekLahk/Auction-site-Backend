package com.ibay.backend.dao.h2;


import com.ibay.backend.dao.postgres.AuctionDataAccessServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Profile("development")
@Repository
public class AuctionDataAccessServiceH2 extends AuctionDataAccessServicePostgres {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuctionDataAccessServiceH2(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    //Overwrite any Postgres AuctionDataAccessService methods, that dont work with h2 here!
}
