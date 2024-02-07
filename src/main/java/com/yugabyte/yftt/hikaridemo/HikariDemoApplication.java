package com.yugabyte.yftt.hikaridemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class HikariDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HikariDemoApplication.class, args);
    }

}
