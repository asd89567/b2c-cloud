package com.bocky.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bocky.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;



public interface UserMapper extends BaseMapper<User> {

}
