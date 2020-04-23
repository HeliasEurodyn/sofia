package com.crm.sofia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SofiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SofiaApplication.class, args);
    }

}
