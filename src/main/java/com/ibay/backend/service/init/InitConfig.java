package com.ibay.backend.service.init;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.admin.init")
@Getter
@Setter
public class InitConfig {

    private String username;
    private String email;
    private String password;

    public InitConfig() {
    }
}
