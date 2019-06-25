package com.springboot.bet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = "com.springboot.bet.dao", annotationClass = Repository.class)
@EnableScheduling
public class BetApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetApplication.class, args);
    }

}
