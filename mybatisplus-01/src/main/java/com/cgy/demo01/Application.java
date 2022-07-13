package com.cgy.demo01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author skylz
 */
@SpringBootApplication
@MapperScan("com.cgy.demo01.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
