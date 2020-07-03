package com.nie.sign.aciivity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nie.sign.R;
import com.nie.sign.base.BaseActivity;
import com.nie.sign.presenter.ChangePasswordPresenter;
import com.nie.sign.presenter.EmailCodePresenter;
import com.nie.sign.presenter.ForgetEmailCodePresenter;
import com.nie.sign.presenter.RegisterPresenter;

public class ForgetPasswordActivity extends BaseActivity {
    public static final int KEEP_TIME_MIN = 1;
    public static final int RESET_TIME = 2;
    public int time = 60;

    public EditText etUserAccount;
    public EditText etUserEmail;
    public EditText edPassword;
    public EditText edPassword2;
    public EditText etUserCode;
    public Button btnUserCode;
    public Button btnRegister;
    public Button btnBreak;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case KEEP_TIME_MIN:
                    time--;
                    //时间更新在页面上
                    btnUserCode.setText("稍后再发(" + time + ")");
                    break;
                case RESET_TIME:
                    btnUserCode.setEnabled(true);
                    time = 60;
                    //时间更新在页面上
                    btnUserCode.setText("重新获取验证码");
                    break;

            }
        }
    };


    @Override
    protected int initLayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected void initView() {

        etUserAccount = (EditText) findViewById(R.id.et_user_account);
        etUserEmail = (EditText) findViewById(R.id.et_user_email);
        edPassword = (EditText) findViewById(R.id.ed_password);
        edPassword2 = (EditText) findViewById(R.id.ed_password2);
        etUserCode = (EditText) findViewById(R.id.et_user_code);
        btnUserCode = (Button) findViewById(R.id.btn_user_code);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnBreak = (Button) findViewById(R.id.btn_break);

    }

    /**
     * 点击获取验证码回调
     */
    public void next(View view) {
        boolean verification = verification();
        if (verification) {
            ForgetEmailCodePresenter forgetEmailCodePresenter = new ForgetEmailCodePresenter(this);
            forgetEmailCodePresenter.sendEmailCode();
        }
    }

    public void back(View view) {
        finish();
    }


    /**
     * 点击注册回调
     */
    public void change(View view) {
        boolean verification = verification();
        String code = etUserCode.getText().toString();
        if (verification) {
            if(!TextUtils.isEmpty(code)){
                ChangePasswordPresenter changePasswordPresenter = new ChangePasswordPresenter(this);
                changePasswordPresenter.change();
            }else {
                Toast.makeText(context, "请输入验证码！", Toast.LENGTH_SHORT).show();
            }

        }

    }


    //验证表单数据是否合法
    public boolean verification() {

        boolean isVerification = true;

        String account = etUserAccount.getText().toString();
        String email = etUserEmail.getText().toString();
        String password = edPassword.getText().toString();
        String password2 = edPassword2.getText().toString();

        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(email) &&
                !TextUtils.isEmpty(password) && !TextUtils.isEmpty(password)) {
            if (password.equals(password2)) {
                if (email.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
                    if (password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-z]{6,20}$")) {

                        if(account.length()>=4 && account.length()<=9){
                            isVerification = true;
                        }else {
                            isVerification = false;
                            Toast.makeText(context, "账号长度必须为4到9位", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        isVerification = false;
                        Toast.makeText(context, "密码必须长度为6-20位包含数字和字母", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    isVerification = false;
                    Toast.makeText(context, "请输入合法的邮箱！", Toast.LENGTH_SHORT).show();
                }

            } else {
                isVerification = false;
                Toast.makeText(context, "两次密码不一致", Toast.LENGTH_SHORT).show();
            }

        } else {
            isVerification = false;
            Toast.makeText(context, "请输入空白的区域！", Toast.LENGTH_SHORT).show();
        }

        return isVerification;
    }

    @Override
    protected void initData() {

    }


}
