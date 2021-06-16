package com.codemonkeys.backendcoin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.codemonkeys.backendcoin.mapper")
@SpringBootApplication
public class BackendCoinApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendCoinApplication.class, args);
    }

}
