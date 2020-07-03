package com.nie.sign.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nie.sign.R;
import com.nie.sign.aciivity.LoginActivity;
import com.nie.sign.aciivity.MyClassActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.base.BaseFragment;

public class MyFragment extends BaseFragment {
    private TextView tvAccount;
    private TextView tvEmail;
    private TextView tvRole;
    private Button btnMyCourse;
    private Button btnExit;

    @Override
    protected View initView() {

        View view = View.inflate(getContext(), R.layout.fragemnt_my, null);
        tvAccount = (TextView) view.findViewById(R.id.tv_account);
        tvEmail = (TextView) view.findViewById(R.id.tv_email);
        tvRole = (TextView) view.findViewById(R.id.tv_role);
        btnMyCourse = (Button) view.findViewById(R.id.btn_my_course);
        btnExit = (Button) view.findViewById(R.id.btn_exit);


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        // .setIcon(android.R.drawable.ic_menu_info_detailsp)
                        .setTitle("提示").setMessage("你确定要退出登录吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        getActivity().finish();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                }).show();
            }
        });

        btnMyCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyClassActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    protected void initData() {
        super.initData();
        tvAccount.setText("账号：" + SystemApplication.userInfo.getAccount());
        tvEmail.setText("邮箱：" + SystemApplication.userInfo.getEmail());
        if (SystemApplication.userInfo.getRoleName().equals("admin")) {
            tvRole.setText("角色：教师");
        } else {
            tvRole.setText("角色：学生");
        }

    }
}
