package com.cgy.demo;

import com.cgy.demo.entity.User;
import com.cgy.demo.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@MapperScan("com.cgy.demo.mapper")
public class Program implements CommandLineRunner {

    @Autowired
    UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }
}
