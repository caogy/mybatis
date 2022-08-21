package com.cgy.demo.entity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}

