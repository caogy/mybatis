package com.cgy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author skylz
 */
@SpringBootApplication
@MapperScan("com.cgy.**.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}
