package com.ibay.backend.model;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

public class Auction {

    @Getter private final String title;

    @Getter private final String description;

    @Getter private final Integer duration;

    @Getter private final String ownerID;

    @Getter private final List<String> imageURLList;

    @Getter private final Timestamp endTime;


    public Auction(String title, String description, Integer duration, String ownerID, List<String> imageURLList, Timestamp endTime) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.ownerID = ownerID;
        this.imageURLList = imageURLList;
        this.endTime = endTime;
    }

    public Auction(String title, String description, Integer duration, String ownerID, List<String> imageURLList, String endTime) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.ownerID = ownerID;
        this.imageURLList = imageURLList;
        this.endTime = ConversionFunctions.parseTimestampString(endTime);
    }
}
