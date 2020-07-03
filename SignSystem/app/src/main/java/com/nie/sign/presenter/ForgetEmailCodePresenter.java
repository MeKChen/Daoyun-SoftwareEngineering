package com.nie.sign.presenter;

import android.widget.Toast;

import com.nie.sign.aciivity.ForgetPasswordActivity;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.LoadingDialog;

import retrofit2.Call;

public class ForgetEmailCodePresenter extends BasePresenter {
    private ForgetPasswordActivity forgetPasswordActivity;

    public ForgetEmailCodePresenter(ForgetPasswordActivity forgetPasswordActivity) {
        this.forgetPasswordActivity = forgetPasswordActivity;

    }

    @Override
    protected void showError(String message) {
        LoadingDialog.disDialog();
        Toast.makeText(forgetPasswordActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void parseJson(String json) {
        LoadingDialog.disDialog();
        Toast.makeText(forgetPasswordActivity, "验证码发送成功，请及时查看", Toast.LENGTH_SHORT).show();

        forgetPasswordActivity.btnUserCode.setEnabled(false);
        //倒计时  timerTask  handler
        new Thread() {
            @Override
            public void run() {
                //每个1秒钟减少数组1
                while (forgetPasswordActivity.time > 0) {
                    //通过hanlder机制,告知主线程更新时间,更新时间周期,1秒钟更新一次
                    forgetPasswordActivity.handler.sendEmptyMessage(forgetPasswordActivity.KEEP_TIME_MIN);
                    try {
                        Thread.sleep(999);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //在60秒的计时过程中没有获取到验证码,重新获取验证码
                forgetPasswordActivity.handler.sendEmptyMessage(forgetPasswordActivity.RESET_TIME);
            }
        }.start();

    }

    public void sendEmailCode() {
        LoadingDialog.showDialog(forgetPasswordActivity);
        String email = forgetPasswordActivity.etUserEmail.getText().toString();
        Call<ResponseInfo> loginInfo = responseInfoAPI.sendForgetEmailCode(email, "FORGETPWD");
        loginInfo.enqueue(new BasePresenter.CallBackAdapter());
    }
}
