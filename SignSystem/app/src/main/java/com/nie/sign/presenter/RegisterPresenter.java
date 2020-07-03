package com.nie.sign.presenter;

import android.content.Intent;
import android.widget.Toast;

import com.nie.sign.aciivity.LoginActivity;
import com.nie.sign.aciivity.RegisterActivity;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.LoadingDialog;

import retrofit2.Call;

public class RegisterPresenter extends BasePresenter {
    private RegisterActivity registerActivity;

    public RegisterPresenter(RegisterActivity registerActivity) {
        this.registerActivity = registerActivity;
    }

    @Override
    protected void showError(String message) {
        LoadingDialog.disDialog();
        Toast.makeText(registerActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void parseJson(String json) {
        LoadingDialog.disDialog();
        Toast.makeText(registerActivity, "注册成功！", Toast.LENGTH_SHORT).show();
        registerActivity.finish();
        Intent intent = new Intent(registerActivity, LoginActivity.class);
        registerActivity.startActivity(intent);
    }


    public void register() {
        String account = registerActivity.etUserAccount.getText().toString();
        String email = registerActivity.etUserEmail.getText().toString();
        String password = registerActivity.edPassword.getText().toString();
        String code = registerActivity.etUserCode.getText().toString();

        LoadingDialog.showDialog(registerActivity);
        Call<ResponseInfo> loginInfo = responseInfoAPI.register(account, password,email,code);
        loginInfo.enqueue(new CallBackAdapter());
    }
}
