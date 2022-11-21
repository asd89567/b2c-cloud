package com.bocky.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bocky.Service.UserService;
import com.bocky.constant.UserConstant;
import com.bocky.mapper.UserMapper;
import com.bocky.parm.UserCheckParm;
import com.bocky.parm.UserLoginParm;
import com.bocky.pojo.User;
import com.bocky.utils.MD5Util;
import com.bocky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceimpl  implements UserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public R check(UserCheckParm userCheckParm) {
        //參數封裝
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("user_name",userCheckParm.getUserName());
        //數據庫查詢
        Long total = userMapper.selectCount(userWrapper);
        if (total==0){
            log.info("帳號可以使用");
            //不存在
            return R.ok("帳號不存在,可以使用");
        }
         return R.fail("帳號已存在,不可註冊");
    }

    /**
     * 註冊業務
     *  1.檢查帳號是否存在
     *  2.密碼加密
     *  3.擦入資料庫
     *  4.返回解果封裝
     * @param user 參數以轎驗，密碼還為明文
     * @return
     */
    @Override
    public R register(User user) {
        //1
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("user_name",user.getUserName());
        Long total = userMapper.selectCount(userWrapper);
        if (total>0){
            return R.fail("帳號已存在");
        }
        //2 MD5
        String encode = MD5Util.encode(user.getPassword() + UserConstant.User_SLAT);
        user.setPassword(encode);

        int insert = userMapper.insert(user);

        if (insert==1){
            return R.ok("註冊成功");
        }
        return R.fail("註冊失敗");

    }

    /**
     * 1.密碼加密
     * 2.帳號密碼資料庫查詢,返回一個user對象
     * 3.判斷結果
     * @param userLoginParm
     * @return
     */
    @Override
    public R login(UserLoginParm userLoginParm) {
        String pwd = MD5Util.encode(userLoginParm.getPassword() + UserConstant.User_SLAT);


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userLoginParm.getUserName()).eq("password",pwd);
        User user = userMapper.selectOne(queryWrapper);

        if (user==null){
            R.fail("帳號或者密碼錯誤",user);
        }
        user.setPassword(null);
        return R.ok("登陸成功",user);
    }
}
