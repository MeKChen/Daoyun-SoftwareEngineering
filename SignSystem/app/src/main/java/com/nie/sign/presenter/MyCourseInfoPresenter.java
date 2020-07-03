package com.nie.sign.presenter;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nie.sign.R;
import com.nie.sign.aciivity.ClassDetailActivity;
import com.nie.sign.aciivity.LoginActivity;
import com.nie.sign.aciivity.MyClassActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.presenter.net.bean.CourseInfoBean;
import com.nie.sign.presenter.net.bean.MyCourseInfoBean;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.LoadingDialog;
import com.nie.sign.util.PrefUtils;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;

public class MyCourseInfoPresenter extends BasePresenter {
    private MyClassActivity myClassActivity;

    public MyCourseInfoPresenter(MyClassActivity myClassActivity) {
        this.myClassActivity = myClassActivity;

    }

    @Override
    protected void showError(String message) {
        LoadingDialog.disDialog();
        Toast.makeText(myClassActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void parseJson(String json) {
        LoadingDialog.disDialog();

        final List<MyCourseInfoBean> courseInfoBeans =
                new Gson().fromJson(json, new TypeToken<List<MyCourseInfoBean>>() {
                }.getType());

        Collections.reverse(courseInfoBeans);

        myClassActivity.lvData.setAdapter(new BaseAdapter() {
            private TextView tvName;
            private TextView tvUser;
            private TextView tvDate;

            private TextView tvUid;

            @Override
            public int getCount() {
                return courseInfoBeans.size();
            }

            @Override
            public MyCourseInfoBean getItem(int i) {
                return courseInfoBeans.get(i);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view = View.inflate(myClassActivity, R.layout.home_list_item, null);

                MyCourseInfoBean courseInfoBean = getItem(i);

                tvName = (TextView) view.findViewById(R.id.tv_name);
                tvUser = (TextView) view.findViewById(R.id.tv_user);
                tvDate = (TextView) view.findViewById(R.id.tv_date);
                tvUid = (TextView) view.findViewById(R.id.tv_uid);

                tvUid.setText("班级UID ：" + courseInfoBean.getId());
                tvName.setText("班级名称：" + courseInfoBean.getCourseName());
                tvUser.setText("创建人    ：" + courseInfoBean.getCreateBy());
                tvDate.setText("创建时间：" + courseInfoBean.getCreateDate());

                return view;
            }
        });

        myClassActivity. lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyCourseInfoBean courseInfoBean = courseInfoBeans.get(i);
                String courseName = courseInfoBean.getCourseName();
                Intent intent = new Intent(myClassActivity, ClassDetailActivity.class);
                intent.putExtra("courseName", courseName);
                myClassActivity.startActivity(intent);
            }
        });
    }

    public void getMyCourseInfo() {
        LoadingDialog.showDialog(myClassActivity);

        Call<ResponseInfo> myCourseInfo = responseInfoAPI.getMyCourseInfo(SystemApplication.token,  SystemApplication.userInfo.getAccount());
        myCourseInfo.enqueue(new CallBackAdapter());
    }
}
