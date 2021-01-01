package com.ibay.backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static com.ibay.backend.security.PasswordConfig.passwordEncoder;

public class User implements RowMapper<User> {

    @Getter private String id;
    @Getter private String username;
    @Getter private String email;
    @Getter private String full_name;
    @Getter private Timestamp registrationDate;
    @Getter(onMethod = @__( @JsonIgnore )) private String password;
    @Getter(onMethod = @__( @JsonIgnore )) private String confirmPassword;
    @Getter(onMethod = @__( @JsonIgnore )) private String newPassword;

    public User(@JsonProperty("id") String id,
                @JsonProperty("username") String username,
                @JsonProperty("email") String email,
                @JsonProperty("name") String full_name,
                @JsonProperty("reg_date") Timestamp registrationDate,
                @JsonProperty("password") String password,
                @JsonProperty("confirmPassword") String confirmPassword,
                @JsonProperty("newPassword") String newPassword){
        this.id = id;
        this.username = username;
        this.email = email;
        this.full_name = full_name;
        this.registrationDate = registrationDate;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.newPassword = newPassword;
    }

    public User() {
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
        if (!isEmpty(this.newPassword)) output.put("password", passwordEncoder().encode(this.newPassword));

        return output;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getString("userID"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("full_name"),
                rs.getTimestamp("registration_date"),
                password, confirmPassword, newPassword);
    }


    @JsonIgnore
    public String toTestString() {
        return id+username+email+full_name;
    }

}
