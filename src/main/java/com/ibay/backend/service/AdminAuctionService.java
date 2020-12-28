package com.ibay.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test")
public class AdminAuctionService {

    @Autowired
    public AdminAuctionService() {
    }

    public Boolean deleteAuctionByID(String id) {
        return false;
    }
}
