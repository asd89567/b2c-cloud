package com.bocky.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bocky.Service.AdressService;
import com.bocky.mapper.AdressMapper;
import com.bocky.pojo.Adress;
import com.bocky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class AdressServiceimpl implements AdressService {

    @Resource
    private AdressMapper adressMapper;
    @Override
    public R list(Integer userId) {
        QueryWrapper<Adress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Adress> adresses = adressMapper.selectList(queryWrapper);
        R ok = R.ok("查詢成功", adresses);
        return ok;
    }

    @Override
    public R save(Adress adress) {
        int insert = adressMapper.insert(adress);
        if (insert == 0){
            return R.fail("插入失敗");
        }
        return list(adress.getUserId());
    }

    @Override
    public R remove(Integer id) {
        int i = adressMapper.deleteById(id);
        if (i == 0){
            return R.fail("刪除失敗");
        }
        return R.ok("刪除成功");
    }
}
