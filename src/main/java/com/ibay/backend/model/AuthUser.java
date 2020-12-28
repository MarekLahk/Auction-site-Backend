package com.ibay.backend.model;

import lombok.Getter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthUser implements RowMapper<AuthUser>, UserDetails {

    @NotNull
    @NotEmpty
    @Getter
    private String id;
    @NotNull
    @NotEmpty
    @Getter
    private String username;
    @NotNull
    @NotEmpty
    @Getter
    private String password;
    @Getter
    private Set<SimpleGrantedAuthority> grantedAuthorities;

    public AuthUser(@NotNull @NotEmpty String id,
                    @NotNull @NotEmpty String username,
                    @NotNull @NotEmpty String password,
                    Set<SimpleGrantedAuthority> grantedAuthorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
    }

    public AuthUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public AuthUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        String roles = rs.getString("roles");
        String[] roleArray = roles.split(" ");
        Set<SimpleGrantedAuthority> authorities = Arrays.stream(roleArray).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return new AuthUser(
                rs.getString("userid"),
                rs.getString("username"),
                rs.getString("password"),
                authorities
                );
    }
}
