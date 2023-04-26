package com.bocky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bocky.pojo.Adress;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;


public interface AdressMapper extends BaseMapper<Adress> {
}
