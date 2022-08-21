package com.cgy.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.cgy.demo.mapper")
public class MyBatisPlusConfig {
}
