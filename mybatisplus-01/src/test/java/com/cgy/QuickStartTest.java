package com.cgy;

import com.cgy.quickstart.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuickStartTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        var userList = userMapper.selectList(null);
        System.out.println(userList);
    }


}
