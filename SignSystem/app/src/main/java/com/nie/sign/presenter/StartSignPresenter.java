package com.nie.sign.presenter;

import android.widget.Toast;

import com.nie.sign.aciivity.ClassDetailActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.LoadingDialog;
import com.nie.sign.util.PrefUtils;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;

class StartSignPresenter extends BasePresenter {
    private ClassDetailActivity classDetailActivity;

    public StartSignPresenter(ClassDetailActivity classDetailActivity) {
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
        classDetailActivity.initData();
        Toast.makeText(classDetailActivity, "发起签到成功！一分钟后自动结束签到！", Toast.LENGTH_SHORT).show();
        Timer timer = new Timer();                    //创建一个定时器对象

        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                EndSignPresenter endSignPresenter = new EndSignPresenter(classDetailActivity);
                endSignPresenter.endSignNoUi(classDetailActivity.courseName, true);
            }

        };

        timer.schedule(task, 60000);                //启动定时器，60秒后执行


    }

    public void startSign(String courseName) {
        String latitude = SystemApplication.amapLocation.getLatitude() + "";//纬度
        String longitude = SystemApplication.amapLocation.getLongitude() + "";//经度
        Call<ResponseInfo> startSign = responseInfoAPI.startSign(SystemApplication.token, courseName, SystemApplication.userInfo.getAccount(), longitude, latitude);
        startSign.enqueue(new BasePresenter.CallBackAdapter());
    }
}
