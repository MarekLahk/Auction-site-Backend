package com.ibay.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.jdbc.core.RowMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Auction implements RowMapper<Auction> {

    @Getter private String title;

    @Getter private String description;

    @Getter private Integer duration;

    @Getter private String ownerID;

    @Getter private List<String> imageURLList;

    @Getter private Timestamp endTime;

    @Getter private String category;


    public Auction(@JsonProperty("title") String title,
                   @JsonProperty("description") String description,
                   @JsonProperty("duration") Integer duration,
                   @JsonProperty("ownerID") String ownerID,
                   @JsonProperty("category") String category,
                   List<String> imageURLList,
                   Timestamp endTime) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.ownerID = ownerID;
        this.category = category;
        this.imageURLList = imageURLList;
        this.endTime = endTime;
        calculateEndTime();
    }

    public Auction(String title, String description, Integer duration, String ownerID, String category, List<String> imageURLList, String endTime) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.ownerID = ownerID;
        this.category = category;
        this.imageURLList = imageURLList;
        this.endTime = ConversionFunctions.parseTimestampString(endTime);
        calculateEndTime();
    }

    public Auction() {
    }

    @Override
    public Auction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Auction(
                rs.getString("title"),
                rs.getString("description"),
                null,
                rs.getString("auctionOwner"),
                rs.getString("category"),
                null,
                rs.getTimestamp("endDateTime")
        );
    }

    public Boolean calculateEndTime() {
        if (this.endTime == null && this.duration != null) {

            this.endTime = new Timestamp(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(duration));
            return true;
        }
        return false;
    }




}
