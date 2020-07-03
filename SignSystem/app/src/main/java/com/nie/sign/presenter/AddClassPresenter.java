package com.nie.sign.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.nie.sign.aciivity.AddClassActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.LoadingDialog;
import com.nie.sign.util.PrefUtils;

import retrofit2.Call;

public class AddClassPresenter extends BasePresenter {
    private Context context;

    public AddClassPresenter(Context context) {
        this.context = context;
    }

    @Override
    protected void showError(String message) {
        LoadingDialog.disDialog();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void parseJson(String json) {
        LoadingDialog.disDialog();
        if (!TextUtils.isEmpty(json)) {
            AddClassActivity addClassActivity = (AddClassActivity) this.context;
            addClassActivity.finish();
            Toast.makeText(this.context, "添加成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void addClass(String className) {
        LoadingDialog.showDialog(context);

        Call<ResponseInfo> addClass = responseInfoAPI.addClass(SystemApplication.token, className, SystemApplication.userInfo.getAccount());
        addClass.enqueue(new BasePresenter.CallBackAdapter());
    }


}
