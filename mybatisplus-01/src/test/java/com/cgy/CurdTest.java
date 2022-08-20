package com.cgy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cgy.curd.mapper.User1Mapper;
import com.cgy.curd.mapper.User2Mapper;
import com.cgy.curd.model.User;
import lombok.var;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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

        //新增
        user1Mapper.insert(new User().setId(3L).setAge(28).setName("cgy").setEmail("cgy@qq.com"));
        user1Mapper.insert(new User().setAge(28).setName("Sandy").setEmail("abc@qq.com"));
    }

    @Test
    public void select() {
        Integer c = user1Mapper.selectCount(new QueryWrapper<User>().lambda().eq(User::getId, 10086));
        if (c == 0) {
            var user = new User().setId(10086L)
                    .setAge(3)
                    .setEmail("abc@qq.com")
                    .setName("miemie");
            user1Mapper.insert(user);
        }

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

    @Test
    public void update() {
        //更新1
//        user1Mapper.updateById(new User().setId(1L).setEmail("ddd@cc.com"));

        //更新2
//        user1Mapper.update(new User().setName("mp"),
//                Wrappers.<User>lambdaUpdate()
//                        .set(User::getAge, 3)
//                        .set(User::getEmail, null)
//                        .eq(User::getId, 2));

        //更新3
//        user1Mapper.update(new User().setEmail("kkk@qq.com"),
//                new QueryWrapper<User>().lambda().eq(User::getId, 2));

        //更新4
        user1Mapper.update(new User().setAge(30),
                new UpdateWrapper<User>().lambda().set(User::getName, "wo").eq(User::getId, 2));

        //更新5
        user1Mapper.update(null,
                new UpdateWrapper<User>().lambda().set(User::getName, "wo").eq(User::getId, 2));

    }

    @Test
    public void orderBy() {
        var lst = user1Mapper.selectList(Wrappers.<User>query().orderByAsc("age"));
        user1Mapper.selectList(Wrappers.<User>query().orderByAsc("age", "name"));
        user1Mapper.selectList(Wrappers.<User>query().orderByAsc("age").orderByDesc("name"));

    }

    @Test
    public void selectMaps() {
//        var mapList = user1Mapper.selectMaps(null);

        //cgy 这个没有分页？？？
        IPage<Map<String, Object>> page = user1Mapper.selectMapsPage(new Page<>(1, 5), Wrappers.<User>query().orderByDesc("age"));
    }

    @Test
    public void testSelectMaxId() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        User user = user1Mapper.selectOne(wrapper);
    }

    @Test
    public void testGroup() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("age,count(*)").groupBy("age").orderByAsc("age");

        //List<Map<String, Object>> mapList = user1Mapper.selectMaps(wrapper);

        var userList = user1Mapper.selectList(Wrappers.<User>lambdaQuery().select(User::getAge).groupBy(User::getAge).orderByAsc(User::getAge));

    }


}
