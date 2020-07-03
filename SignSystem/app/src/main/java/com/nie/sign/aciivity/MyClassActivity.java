package com.nie.sign.aciivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.nie.sign.R;
import com.nie.sign.base.BaseActivity;
import com.nie.sign.presenter.MyCourseInfoPresenter;

public class MyClassActivity extends BaseActivity {
    public ImageView ivBack;
    public ListView lvData;

    @Override
    protected int initLayout() {
        return R.layout.activity_my_class;
    }

    @Override
    protected void initView() {


        ivBack = (ImageView) findViewById(R.id.iv_back);
        lvData = (ListView) findViewById(R.id.lv_data);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        MyCourseInfoPresenter MyCourseInfoPresenter = new MyCourseInfoPresenter(this);
        MyCourseInfoPresenter.getMyCourseInfo();
    }
}
