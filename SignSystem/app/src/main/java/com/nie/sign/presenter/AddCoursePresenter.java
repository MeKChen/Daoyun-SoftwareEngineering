package com.nie.sign.presenter;

import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.nie.sign.aciivity.ClassDetailActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.LoadingDialog;
import com.nie.sign.util.PrefUtils;

import retrofit2.Call;

class AddCoursePresenter extends BasePresenter {
    private ClassDetailActivity classDetailActivity;

    public AddCoursePresenter(ClassDetailActivity classDetailActivity) {
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
        Toast.makeText(classDetailActivity, "加入成功！", Toast.LENGTH_SHORT).show();
        classDetailActivity.btnAddClass.setText("已加入");
        classDetailActivity.btnAddClass.setEnabled(false);
        classDetailActivity.llContent.setVisibility(View.VISIBLE);
        CourseInfoDetailPresenter courseInfoDetailPresenter = new CourseInfoDetailPresenter(classDetailActivity);
        courseInfoDetailPresenter.getCourseInfoDetail();
    }

    public void addCourse(String courseName) {
        LoadingDialog.showDialog(classDetailActivity);
        Call<ResponseInfo> addCourse = responseInfoAPI.addCourse(SystemApplication.token, courseName,SystemApplication.userInfo.getAccount());
        addCourse.enqueue(new BasePresenter.CallBackAdapter());
    }
}
