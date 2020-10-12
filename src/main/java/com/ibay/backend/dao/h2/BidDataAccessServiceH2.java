package com.ibay.backend.dao.h2;


import com.ibay.backend.dao.BidDao;
import com.ibay.backend.dao.postgres.BidDataAccessServicePostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Profile("development")
@Repository
public class BidDataAccessServiceH2 extends BidDataAccessServicePostgres {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BidDataAccessServiceH2(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    //Overwrite any Postgres BidDataAccessService methods, that dont work with h2 here!

}
