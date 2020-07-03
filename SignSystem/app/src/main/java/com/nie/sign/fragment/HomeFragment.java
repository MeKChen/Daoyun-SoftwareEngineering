package com.nie.sign.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nie.sign.MainActivity;
import com.nie.sign.R;
import com.nie.sign.aciivity.AddClassActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.base.BaseFragment;
import com.nie.sign.presenter.AllCourseInfoPresenter;
import com.nie.sign.zxing.android.CaptureActivity;

public class HomeFragment extends BaseFragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private EditText edKeyword;
    private Button btnSearch;
    private ListView lvData;
    private AllCourseInfoPresenter allCourseInfoPresenter;

    private FloatingActionButton btnAdd;
    private FloatingActionButton btnScan;

    private static final int REQUEST_CODE_SCAN = 0x0000;

    @SuppressLint("RestrictedApi")
    @Override
    protected View initView() {
        View view = View.inflate(getContext(), R.layout.fragemnt_home, null);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        edKeyword = (EditText) view.findViewById(R.id.ed_keyword);
        btnSearch = (Button) view.findViewById(R.id.btn_search);
        lvData = (ListView) view.findViewById(R.id.lv_data);

        swipeRefreshLayout.setColorSchemeResources(new int[]{R.color.colorPrimaryDark});

        btnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);
        btnScan = (FloatingActionButton) view.findViewById(R.id.btn_scan);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allCourseInfoPresenter.getAllCourseInfo();
            }
        });

        //为教师
        if (SystemApplication.userInfo.getRoleName().equals("admin")) {

            btnAdd.setVisibility(View.VISIBLE);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), AddClassActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            //为学生
            btnScan.setVisibility(View.VISIBLE);
            btnScan.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                    } else {
                        Intent intent = new Intent(getActivity(), CaptureActivity.class);
                        startActivityForResult(intent, REQUEST_CODE_SCAN);
                    }

                }
            });
        }

        return view;
    }

    @Override
    protected void initData() {

        allCourseInfoPresenter = new AllCourseInfoPresenter(
                getActivity(),
                swipeRefreshLayout,
                lvData,
                edKeyword,
                SystemApplication.token);

        allCourseInfoPresenter.getAllCourseInfo();


    }

    @Override
    public void onResume() {
        super.onResume();
        if (allCourseInfoPresenter != null) {
            allCourseInfoPresenter.getAllCourseInfo();
        }
    }
}
