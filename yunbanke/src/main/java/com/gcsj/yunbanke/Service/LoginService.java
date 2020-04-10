package com.gcsj.yunbanke.Service;

import com.gcsj.yunbanke.Entity.UserInfo;

public interface LoginService {
    UserInfo checkLogin(String uname, String pwd);

    Integer createUser(UserInfo user);
}
