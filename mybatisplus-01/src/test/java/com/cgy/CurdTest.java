package com.cgy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cgy.curd.mapper.User2Mapper;
import com.cgy.curd.mapper.User1Mapper;
import com.cgy.curd.model.User;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurdTest {

    @Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("小杨").setAge(3).setEmail("abc@qq.com");
        int c = user1Mapper.insert(user);
        Assertions.assertThat(c).isGreaterThan(0);
    }

    @Test
    public void delete() {
        int c1 = user1Mapper.deleteById(3L);
        int c2 = user1Mapper.delete(new QueryWrapper<User>().lambda().eq(User::getName, "Sandy"));
    }

    @Test
    public void select() {

//        var user = new User().setId(10086L)
//                .setAge(3)
//                .setEmail("abc@qq.com")
//                .setName("miemie");
//
//        user1Mapper.insert(user);

        var u1 = user1Mapper.selectById(10086);
        var u2 = user1Mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 10086));

        user1Mapper.selectList(Wrappers.<User>lambdaQuery().select(User::getId, User::getEmail)).forEach(x -> {
            System.out.println(x);
            Assertions.assertThat(x.getId()).isNotNull();
            Assertions.assertThat(x.getEmail()).isNotNull();
            Assertions.assertThat(x.getName()).isNull();
            Assertions.assertThat(x.getAge()).isNull();
        });

        //同上一致
        user1Mapper.selectList(new QueryWrapper<User>().select("id", "email")).forEach(x -> {
            System.out.println(x);
            Assertions.assertThat(x.getId()).isNotNull();
            Assertions.assertThat(x.getEmail()).isNotNull();
            Assertions.assertThat(x.getName()).isNull();
            Assertions.assertThat(x.getAge()).isNull();
        });


    }
}
