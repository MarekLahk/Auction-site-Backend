package com.ibay.backend.scheduler;


import com.ibay.backend.dao.AuthDao;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleTokenCleanup {

    private final AuthDao authDao;

    public ScheduleTokenCleanup(AuthDao authDao) {
        this.authDao = authDao;
    }

    @Scheduled(fixedDelay = 600000, initialDelay = 1200000)
    public void TokenCleanup() {
        System.out.println("Cleaning tokens");
        authDao.cleanTokenTable();
    }
}
