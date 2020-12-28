package com.ibay.backend.service;

import com.ibay.backend.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final AuthDao authDao;

    @Autowired
    public ApplicationUserService(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  authDao.selectUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));

    }
}
