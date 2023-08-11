package com.crm.sofia;

import com.crm.sofia.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableCaching
@EnableScheduling
public class SofiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SofiaApplication.class, args);
    }

}
