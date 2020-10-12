package com.ibay.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.jdbc.core.RowMapper;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Getter
public class Bid implements RowMapper<Bid> {

    private UUID bidID;

    private String auctionID;

    private String bidOwnerID;

    private BigDecimal bidAmount;

    public Bid(@JsonProperty("id") UUID bidID,
               @JsonProperty("auctionID") @NotNull String auctionID,
               @JsonProperty("bidOwnerID") @NotNull String bidOwnerID,
               @JsonProperty("bidAmount") @NotNull BigDecimal bidAmount) {
        this.bidID = bidID;
        this.auctionID = auctionID;
        this.bidOwnerID = bidOwnerID;
        this.bidAmount = bidAmount;
    }

    public Bid(){}

    @Override
    public Bid mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Bid(
                rs.getObject("bidID", UUID.class),
                rs.getString("bidAuctionID"),
                rs.getString("bidOwnerID"),
                rs.getBigDecimal("bidAmount")
        );
    }
}
