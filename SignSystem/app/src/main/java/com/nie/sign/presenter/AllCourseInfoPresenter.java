package com.nie.sign.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nie.sign.R;
import com.nie.sign.aciivity.ClassDetailActivity;
import com.nie.sign.presenter.net.bean.CourseInfoBean;
import com.nie.sign.presenter.net.bean.ResponseInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;

public class AllCourseInfoPresenter extends BasePresenter {

    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView lvData;
    private EditText edKeyword;
    private String token;


    @Override
    protected void showError(String message) {
        swipeRefreshLayout.setRefreshing(false);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void parseJson(String json) {
        swipeRefreshLayout.setRefreshing(false);
        final List<CourseInfoBean> courseInfoBeans =
                new Gson().fromJson(json, new TypeToken<List<CourseInfoBean>>() {
                }.getType());

        String etKeyword = edKeyword.getText().toString();

        final ArrayList<CourseInfoBean> result = new ArrayList<>();

        if (!TextUtils.isEmpty(etKeyword)) {
            for (CourseInfoBean courseInfoBean : courseInfoBeans) {
                if ((courseInfoBean.getId() + "").equals(etKeyword)) {
                    result.add(courseInfoBean);
                }
            }
            if (result.size() == 0) {
                Toast.makeText(context, "没有查询到对应UID的班级", Toast.LENGTH_SHORT).show();
                result.addAll(courseInfoBeans);
            }
        } else {
            result.addAll(courseInfoBeans);
        }


        Collections.reverse(result);

        lvData.setAdapter(new BaseAdapter() {
            private TextView tvName;
            private TextView tvUser;
            private TextView tvDate;

            private TextView tvUid;

            @Override
            public int getCount() {
                return result.size();
            }

            @Override
            public CourseInfoBean getItem(int i) {
                return result.get(i);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view = View.inflate(context, R.layout.home_list_item, null);

                CourseInfoBean courseInfoBean = getItem(i);

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

        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CourseInfoBean courseInfoBean = result.get(i);
                String courseName = courseInfoBean.getCourseName();
                Intent intent = new Intent(context, ClassDetailActivity.class);
                intent.putExtra("courseName", courseName);
                context.startActivity(intent);
            }
        });
    }


    public AllCourseInfoPresenter(Context context,
                                  SwipeRefreshLayout swipeRefreshLayout,
                                  ListView lvData,
                                  EditText edKeyword,
                                  String token) {
        this.context = context;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.lvData = lvData;
        this.edKeyword = edKeyword;
        this.token = token;

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllCourseInfo();
            }
        });
    }

    //请求网络的触发方法
    public void getAllCourseInfo() {
        swipeRefreshLayout.setRefreshing(true);
        Call<ResponseInfo> loginInfo = responseInfoAPI.getAllCourseInfo(token);
        loginInfo.enqueue(new CallBackAdapter());
    }

}
