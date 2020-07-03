package com.nie.sign.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nie.sign.MainActivity;
import com.nie.sign.aciivity.LoginActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.presenter.net.bean.UserInfoBean;
import com.nie.sign.util.LoadingDialog;
import com.nie.sign.util.PrefUtils;

import retrofit2.Call;

class UserInfoPresenter extends BasePresenter {

    private Context mCtx;

    public UserInfoPresenter(Context mCtx) {
        this.mCtx = mCtx;
    }

    @Override
    protected void showError(String message) {

    }

    @Override
    protected void parseJson(String json) {
        LoadingDialog.disDialog();

        UserInfoBean userInfoBean = new Gson().fromJson(json, UserInfoBean.class);

        SystemApplication.userInfo = userInfoBean;
        //页面的结束
        ((LoginActivity) mCtx).finish();
        //跳转到首页
        Intent intent = new Intent(mCtx, MainActivity.class);

        mCtx.startActivity(intent);

        Toast.makeText(mCtx, "登录成功！", Toast.LENGTH_SHORT).show();
    }

    public void getUserInfo() {

        Call<ResponseInfo> loginInfo = responseInfoAPI.getUserInfo(SystemApplication.token);
        loginInfo.enqueue(new CallBackAdapter());
    }
}
