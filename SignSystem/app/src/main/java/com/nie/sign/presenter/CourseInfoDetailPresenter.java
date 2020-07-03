package com.nie.sign.presenter;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nie.sign.aciivity.ClassDetailActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.presenter.net.bean.CourseInfoDetailBean;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.ActivityManager;
import com.nie.sign.util.LoadingDialog;
import com.nie.sign.util.PrefUtils;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class CourseInfoDetailPresenter extends BasePresenter {


    private ClassDetailActivity classDetailActivity;
    private List<CourseInfoDetailBean> courseInfoDetailBeans;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (ActivityManager.isForeground(SystemApplication.getContext(), "ClassDetailActivity")) {
                if (courseBean.getStatus() == 1) {
                    classDetailActivity.initData();
                }
                handler.sendEmptyMessageDelayed(0, 5000);
            }

        }
    };
    private CourseInfoDetailBean.CourseBean courseBean;

    public CourseInfoDetailPresenter(ClassDetailActivity classDetailActivity) {
        this.classDetailActivity = classDetailActivity;
        handler.sendEmptyMessageDelayed(0, 5000);
    }


    @Override
    protected void showError(String message) {
        LoadingDialog.disDialog();
        Toast.makeText(classDetailActivity, "未找到此班级", Toast.LENGTH_SHORT).show();
        classDetailActivity.finish();

    }

    @Override
    protected void parseJson(String json) {
        if(!classDetailActivity.isFinishing()){
            LoadingDialog.disDialog();
        }


        courseInfoDetailBeans = new Gson().fromJson(json, new TypeToken<List<CourseInfoDetailBean>>() {
        }.getType());


        courseBean = courseInfoDetailBeans.get(0).getCourse();


        Map data = calculationSign();
        classDetailActivity.tvSignStatusDetail.setText("签到人数：" + data.get("num") + " / " + "签到率：" + data.get("rate"));

        if (courseBean.getStatus() == 1) {

            classDetailActivity.tvSignStatus.setText("签到状态：" + "正在签到");

            if (SystemApplication.userInfo.getRoleName().equals("admin")) {
                classDetailActivity.btnSignLaunch.setText("提前结束");
                classDetailActivity.btnSignLaunch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EndSignPresenter endSignPresenter = new EndSignPresenter(classDetailActivity);
                        endSignPresenter.endSign(classDetailActivity.courseName);
                    }
                });
            } else {
                if(!isSing()){
                    classDetailActivity.btnSignLaunch.setText("签到");
                    classDetailActivity.btnSignLaunch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            SignPresenter signPresenter = new SignPresenter(classDetailActivity);
                            signPresenter.sign(classDetailActivity.courseName);
                        }
                    });
                }else {
                    classDetailActivity.btnSignLaunch.setText("已签到");
                    classDetailActivity.btnSignLaunch.setEnabled(false);
                }

            }

        } else {
            if (SystemApplication.userInfo.getRoleName().equals("admin")) {
                classDetailActivity.tvSignStatus.setText("签到状态：" + "已结束");
                classDetailActivity.btnSignLaunch.setText("发起签到");
                classDetailActivity.btnSignLaunch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if ((courseInfoDetailBeans.size()) > 0) {

                            StartSignPresenter startSignPresenters = new StartSignPresenter(classDetailActivity);
                            startSignPresenters.startSign(classDetailActivity.courseName);
                        } else {
                            Toast.makeText(classDetailActivity, "当前班级没有学生，无法发起签到！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                classDetailActivity.btnSignLaunch.setVisibility(View.GONE);
            }

        }

        classDetailActivity.tvClassUid.setText("班级UID：" + courseBean.getId());
        classDetailActivity.tvClassName.setText("班级名称：" + courseBean.getCourseName());
        classDetailActivity.tvClassTea.setText("创建人：" + courseBean.getCreateBy());
        classDetailActivity.tvClassDate.setText("创建时间：" + courseBean.getCreateDate());
        classDetailActivity.tvClassStuNum.setText("班级人数：" + (courseInfoDetailBeans.size() - 1) + "人");

        if (SystemApplication.userInfo.getRoleName().equals("admin")) {
            classDetailActivity.btnAddClass.setVisibility(View.GONE);
        } else {
            if (isAddClass()) {
                classDetailActivity.btnAddClass.setText("已加入");
                classDetailActivity.btnAddClass.setEnabled(false);
            } else {
                classDetailActivity.btnAddClass.setText("加入班级");
                classDetailActivity.btnAddClass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddCoursePresenter addCoursePresenter = new AddCoursePresenter(classDetailActivity);
                        addCoursePresenter.addCourse(classDetailActivity.courseName);
                    }
                });
            }
        }

        if (!isAddClass()) {
            classDetailActivity.llContent.setVisibility(View.GONE);
        }

        courseInfoDetailBeans.remove(0);

        classDetailActivity.lvStuData.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return courseInfoDetailBeans.size();
            }

            @Override
            public CourseInfoDetailBean getItem(int i) {
                return courseInfoDetailBeans.get(i);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                CourseInfoDetailBean courseInfoDetailBean = getItem(i);
                CourseInfoDetailBean.UserInfoBean userInfo = courseInfoDetailBean.getUserInfo();
                view = View.inflate(classDetailActivity, android.R.layout.simple_list_item_1, null);
                TextView tvText = view.findViewById(android.R.id.text1);
                tvText.setTextSize(14);
                tvText.setPadding(10, 20, 10, 20);
                String signStatus = "";

                if (courseInfoDetailBean.getStatus() == 0) {
                    signStatus = "签到状态：已签到";
                } else {
                    signStatus = "签到状态：未签到";
                }
                tvText.setText("学生账号：" + userInfo.getAccount() + "\n\n" + "学生邮箱：" + userInfo.getEmail() + "\n\n" + "学生积分：" + courseInfoDetailBean.getScore() + "分\n\n" + signStatus);
                return view;
            }
        });


    }


    //计算人数和签到率
    public Map calculationSign() {
        Map data = new HashMap();
        int num = 0;
        for (int i = 1; i < courseInfoDetailBeans.size(); i++) {
            CourseInfoDetailBean courseInfoDetailBean = courseInfoDetailBeans.get(i);
            if (courseInfoDetailBean.getStatus() == 0) {
                num++;
            }
        }
        data.put("num", num + "人");

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) num / ((float) courseInfoDetailBeans.size() - 1) * 100);

        if (result.contains("NaN")) {
            result = "0";
        }
        data.put("rate", result + "%");

        return data;
    }

    //判断是否在班级内
    public boolean isAddClass() {

        for (CourseInfoDetailBean courseInfoDetailBean : courseInfoDetailBeans) {
            if (courseInfoDetailBean.getUserInfo().getAccount().equals(SystemApplication.userInfo.getAccount())) {
                return true;
            }
        }
        return false;
    }

    //判断当前、登录学生是否签到
    public boolean isSing() {
        for (CourseInfoDetailBean courseInfoDetailBean : courseInfoDetailBeans) {
            if (courseInfoDetailBean.getUserInfo().getAccount().equals(SystemApplication.userInfo.getAccount())) {
               if(courseInfoDetailBean.getStatus() == 0){
                   return true;
               }
            }
        }
        return false;
    }

    public void getCourseInfoDetail() {
        if(!classDetailActivity.isFinishing()){
            LoadingDialog.showDialog(classDetailActivity);
        }

        Call<ResponseInfo> getCourseInfoDetail = responseInfoAPI.getCourseInfoDetail(SystemApplication.token, classDetailActivity.courseName);
        getCourseInfoDetail.enqueue(new BasePresenter.CallBackAdapter());

    }
}
