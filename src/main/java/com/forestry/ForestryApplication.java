package com.forestry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.forestry.dao")
public class ForestryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForestryApplication.class, args);
    }

}

