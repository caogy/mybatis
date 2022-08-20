package com.cgy.demo01.mapper;

import com.cgy.demo01.model.SysUser;

public interface SysUserMapper {
    SysUser selectUserById(Long id);
}
