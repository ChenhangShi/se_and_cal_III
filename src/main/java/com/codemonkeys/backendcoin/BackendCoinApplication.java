package com.codemonkeys.backendcoin;

import com.codemonkeys.backendcoin.util.XmlUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.codemonkeys.backendcoin.mapper")
@SpringBootApplication
public class BackendCoinApplication {

    public static void main(String[] args) {
        XmlUtil.setXmlPath(args[0]);
        SpringApplication.run(BackendCoinApplication.class, args);
    }

}
