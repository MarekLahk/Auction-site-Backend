package com.ibay.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

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
        this.registrationDate = ConversionFunctions.parseTimestampString(registrationDate);
    }


    private boolean isEmpty(String string) {
        return (string == null || string.strip().isEmpty());
    }

    @JsonIgnore
    public Map<String, String> getUpdateFields() {
        Map<String, String> output = new HashMap<>();

        if (!isEmpty(this.full_name)) output.put("full_name", this.getFull_name());
        if (!isEmpty(this.email)) output.put("email", this.email);
        if (!isEmpty(this.username)) output.put("username", this.username);

        return output;
    }


}
