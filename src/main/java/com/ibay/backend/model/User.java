package com.ibay.backend.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.apache.tomcat.jni.Time;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    @Getter private final String id;


    @Getter private final String username;


    @Getter private final String email;


    @Getter private final String full_name;

    @Getter private final Timestamp registrationDate;

    public User(@JsonProperty("id") String id,
                @JsonProperty("username") String username,
                @JsonProperty("email") String email,
                @JsonProperty("name") String full_name,
                @JsonProperty("reg_date") Timestamp registrationDate)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.full_name = full_name;
        this.registrationDate = registrationDate;
    }

    public User(String id,
                String username,
                String email,
                String full_name,
                String registrationDate)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.full_name = full_name;
        this.registrationDate = parseTimestampString(registrationDate);
    }

    private static Timestamp parseTimestampString(String timestampString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(timestampString);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }




}
