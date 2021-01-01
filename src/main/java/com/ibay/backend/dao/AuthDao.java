package com.ibay.backend.dao;

import com.ibay.backend.model.AuthUser;

import java.util.Optional;

public interface AuthDao {

    Optional<AuthUser> selectUserByUsername(String username);

    Boolean isTokenActive(String id);
}
