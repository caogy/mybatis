package com.cgy.demo;

import com.cgy.demo.entity.User;
import com.cgy.demo.mapper.UserMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class TestProgram {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }
}
