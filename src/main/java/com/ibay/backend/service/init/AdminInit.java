package com.ibay.backend.service.init;


import com.ibay.backend.exceptions.userExceptions.EmailTakenException;
import com.ibay.backend.exceptions.userExceptions.UsernameTakenException;
import com.ibay.backend.model.User;
import com.ibay.backend.security.ApplicationUserRole;
import com.ibay.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Initialize first admin account if it doesnt exist already
 */

@Component
public class AdminInit {

    private final UserService userService;
    private final InitConfig initConfig;

    @Autowired
    public AdminInit(UserService userService, InitConfig initConfig) {
        this.userService = userService;
        this.initConfig = initConfig;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        try {
            User adminUser = new User(
                    null,
                    initConfig.getUsername(),
                    initConfig.getEmail(),
                    "Admin1",
                    new Timestamp(System.currentTimeMillis()),
                    initConfig.getPassword(),
                    initConfig.getPassword(),
                    null);
            userService.addUser(adminUser, ApplicationUserRole.ADMIN);
        } catch (EmailTakenException | UsernameTakenException e) {

        }
    }

}
