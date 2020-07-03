package com.nie.sign.presenter;

import android.content.Intent;
import android.widget.Toast;

import com.nie.sign.aciivity.ForgetPasswordActivity;
import com.nie.sign.aciivity.LoginActivity;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.LoadingDialog;

import retrofit2.Call;

public class ChangePasswordPresenter extends BasePresenter{
    private ForgetPasswordActivity forgetPasswordActivity;
    public ChangePasswordPresenter(ForgetPasswordActivity forgetPasswordActivity) {
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
        Toast.makeText(forgetPasswordActivity, "修改密码成功！", Toast.LENGTH_SHORT).show();
        forgetPasswordActivity.finish();
        Intent intent = new Intent(forgetPasswordActivity, LoginActivity.class);
        forgetPasswordActivity.startActivity(intent);
    }


    public void change() {
        String account = forgetPasswordActivity.etUserAccount.getText().toString();
        String email = forgetPasswordActivity.etUserEmail.getText().toString();
        String password = forgetPasswordActivity.edPassword.getText().toString();
        String code = forgetPasswordActivity.etUserCode.getText().toString();

        LoadingDialog.showDialog(forgetPasswordActivity);
        Call<ResponseInfo> loginInfo = responseInfoAPI.changePassword(account, password,email,code);
        loginInfo.enqueue(new CallBackAdapter());
    }
}
