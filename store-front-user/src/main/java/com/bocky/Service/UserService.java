package com.bocky.Service;

import com.bocky.parm.UserCheckParm;
import com.bocky.parm.UserLoginParm;
import com.bocky.pojo.User;
import com.bocky.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface UserService {
    R login(UserLoginParm userLoginParm);

    /**
     * 檢查帳號是否可用
     * @param userCheckParm 帳號參數
     * @return 檢查結果
     */
    R check(UserCheckParm userCheckParm);

    /**
     * 註冊業務
     * @param user 參數以轎驗，密碼還為明文
     * @return 檢查結果
     */
    R register(User user);
}
