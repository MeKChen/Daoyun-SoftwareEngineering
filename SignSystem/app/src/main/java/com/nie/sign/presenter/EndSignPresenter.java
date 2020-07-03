package com.nie.sign.presenter;

import android.widget.Toast;

import com.nie.sign.aciivity.ClassDetailActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.LoadingDialog;
import com.nie.sign.util.PrefUtils;

import retrofit2.Call;

class EndSignPresenter extends BasePresenter {
    private ClassDetailActivity classDetailActivity;
    private boolean isUi = false;

    public EndSignPresenter(ClassDetailActivity classDetailActivity) {
        this.classDetailActivity = classDetailActivity;
    }

    @Override
    protected void showError(String message) {
        if(!isUi) {
            LoadingDialog.disDialog();
            Toast.makeText(classDetailActivity, message, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void parseJson(String json) {
        if(!isUi) {
            LoadingDialog.disDialog();
            Toast.makeText(classDetailActivity, "结束签到成功！", Toast.LENGTH_SHORT).show();
            classDetailActivity.initData();
        }

    }

    public void endSign(String courseName) {
        LoadingDialog.showDialog(classDetailActivity);
        Call<ResponseInfo> endSign = responseInfoAPI.endSign(SystemApplication.token, courseName, SystemApplication.userInfo.getAccount());
        endSign.enqueue(new BasePresenter.CallBackAdapter());
    }

    public void endSignNoUi(String courseName, boolean isUi) {
        this.isUi = isUi;
        Call<ResponseInfo> endSign = responseInfoAPI.endSign(SystemApplication.token, courseName, SystemApplication.userInfo.getAccount());
        endSign.enqueue(new BasePresenter.CallBackAdapter());
    }
}
