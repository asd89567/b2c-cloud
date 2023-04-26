package com.bocky.Controller;


import com.bocky.Service.UserService;
import com.bocky.parm.UserCheckParm;
import com.bocky.parm.UserLoginParm;
import com.bocky.pojo.User;
import com.bocky.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.naming.Binding;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    /**
     *檢查帳好可用
     * @param userCheckParm 接收檢查帳號實體 內部有矯驗註解
     * @param result 獲取繳驗實體對象
     * @return 返回r對象
     */
    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParm userCheckParm, BindingResult result){
        boolean b = result.hasErrors();
        if (b){
            return R.fail("帳號為空");
        }   
        return userService.check(userCheckParm);
    }
    @PostMapping("register")
    public R register(@RequestBody  @Validated User user,BindingResult result){
        if (result.hasErrors()){
            return R.fail("參數異常，不可註冊");
        }
        return userService.register(user);
    }
    @PostMapping("login")
            public R login(@RequestBody @Validated UserLoginParm userLoginParm, BindingResult result){
                if (result.hasErrors()){
                    return R.fail("參數異常，不可登陸");
        }
        return userService.login(userLoginParm);
    }
}
