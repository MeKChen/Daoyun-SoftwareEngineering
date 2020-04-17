package com.gcsj.yunbanke.Service;

import com.gcsj.yunbanke.Entity.BaseClass.LoginInfor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginInfoService {

    HttpServletRequest getRequest();
    HttpServletResponse getResponse();
    String getRequestPath();
    LoginInfor getLogiInfo();
    String getAccount();
}