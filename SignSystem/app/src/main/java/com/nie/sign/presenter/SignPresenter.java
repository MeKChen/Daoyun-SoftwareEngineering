package com.nie.sign.presenter;

import android.widget.Toast;

import com.nie.sign.aciivity.ClassDetailActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.LoadingDialog;
import com.nie.sign.util.PrefUtils;

import retrofit2.Call;

class SignPresenter extends BasePresenter {
    private ClassDetailActivity classDetailActivity;

    public SignPresenter(ClassDetailActivity classDetailActivity) {
        this.classDetailActivity = classDetailActivity;
    }

    @Override
    protected void showError(String message) {
        LoadingDialog.disDialog();
        Toast.makeText(classDetailActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void parseJson(String json) {
        LoadingDialog.disDialog();
        Toast.makeText(classDetailActivity, "签到成功！", Toast.LENGTH_SHORT).show();
    }

    public void sign(String courseName) {
        LoadingDialog.showDialog(classDetailActivity);
        String latitude = SystemApplication.amapLocation.getLatitude() + "";//纬度
        String longitude = SystemApplication.amapLocation.getLongitude() + "";//经度
        Call<ResponseInfo> sign = responseInfoAPI.sign(SystemApplication.token, courseName, SystemApplication.userInfo.getAccount(), longitude, latitude);
        sign.enqueue(new BasePresenter.CallBackAdapter());

    }
}
