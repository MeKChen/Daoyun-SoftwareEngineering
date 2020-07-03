package com.nie.sign.aciivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nie.sign.R;
import com.nie.sign.base.BaseActivity;
import com.nie.sign.presenter.AddClassPresenter;
import com.nie.sign.presenter.BasePresenter;
import com.nie.sign.presenter.net.bean.ResponseInfo;

import retrofit2.Call;

public class AddClassActivity extends BaseActivity {
    private ImageView ivBack;
    private EditText edClassName;
    private Button btnAdd;
    @Override
    protected int initLayout() {
        return R.layout.activity_add_class;
    }

    @Override
    protected void initView() {

        ivBack = (ImageView) findViewById(R.id.iv_back);
        edClassName = (EditText) findViewById(R.id.ed_className);
        btnAdd = (Button) findViewById(R.id.btn_add);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String className = edClassName.getText().toString();
                if(!TextUtils.isEmpty(className)){
                    AddClassPresenter addClassPresenter = new AddClassPresenter(context);
                    addClassPresenter.addClass(className);
                }else {
                    Toast.makeText(context, "请输入班级名！！", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void initData() {


    }
}
