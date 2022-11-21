package com.bocky.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bocky.pojo.User;

public interface UserMapper extends BaseMapper<User> {
    Long selectCount(QueryWrapper<User> userWrapper);
}
