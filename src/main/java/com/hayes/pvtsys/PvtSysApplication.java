package com.hayes.pvtsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PvtSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(PvtSysApplication.class, args);
    }

}
