package com.nie.sign.aciivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.nie.sign.R;
import com.nie.sign.base.BaseActivity;
import com.nie.sign.presenter.LoginPresenter;
import com.nie.sign.util.PrefUtils;


public class LoginActivity extends BaseActivity {
    private EditText edUsername;
    private EditText edPassword;
    private LoginPresenter loginPresenter;


    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

        edUsername = (EditText) findViewById(R.id.et_user_username);
        edPassword = (EditText) findViewById(R.id.ed_password);

        String phone = PrefUtils.getString("username", "");
        String passWord = PrefUtils.getString("passWord", "");

        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(passWord)) {
            edUsername.setText(phone);
            edPassword.setText(passWord);
        }
    }

    @Override
    protected void initData() {
        loginPresenter = new LoginPresenter(this);
    }


    /**
     * 点击登录回调
     */
    public void forget(View view) {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(intent);

    }

    /**
     * 点击登录回调
     */
    public void login(View view) {
        String username = edUsername.getText().toString();
        String passWord = edPassword.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(passWord)) {

            login(username, passWord);

        } else {
            Toast.makeText(this, "请输入空白的区域", Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 点击注册回调
     */
    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    /**
     * 登录逻辑
     */
    public void login(String phone, String passWord) {
        PrefUtils.setString("username", phone);
        PrefUtils.setString("passWord", passWord);
        loginPresenter.getLoginData(phone, passWord);
    }

    private static String[] PERMISSION = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

    private boolean islacksOfPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ContextCompat.checkSelfPermission(this, permission) ==
                    PackageManager.PERMISSION_DENIED;
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x12) {

        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (islacksOfPermission(PERMISSION[1])) {
            ActivityCompat.requestPermissions(this, PERMISSION, 0x12);
        }
    }

}
