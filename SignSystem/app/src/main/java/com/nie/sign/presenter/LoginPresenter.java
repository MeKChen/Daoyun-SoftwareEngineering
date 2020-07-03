package com.nie.sign.presenter;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.presenter.net.bean.LoginResponse;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.LoadingDialog;
import com.nie.sign.util.PrefUtils;

import retrofit2.Call;


public class LoginPresenter extends BasePresenter {
    private Context mCtx;

    private LoginPresenter loginPresenter;

    public LoginPresenter(Context ctx) {
        this.mCtx = ctx;
    }

    @Override
    protected void showError(String message) {
        LoadingDialog.disDialog();
        Toast.makeText(mCtx, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void parseJson(String json) {

        Gson gson = new Gson();
        LoginResponse loginResponse = gson.fromJson(json, LoginResponse.class);

        PrefUtils.setString("token", loginResponse.getToken());



        SystemApplication.token = loginResponse.getToken();

        //获取用户信息
        UserInfoPresenter userInfoPresenter = new UserInfoPresenter(mCtx);
        userInfoPresenter.getUserInfo();


    }

    //请求网络的触发方法
    public void getLoginData(String account, String password) {
        LoadingDialog.showDialog(mCtx);
        Call<ResponseInfo> loginInfo = responseInfoAPI.getLoginInfo(account, password);
        loginInfo.enqueue(new CallBackAdapter());
    }


}
