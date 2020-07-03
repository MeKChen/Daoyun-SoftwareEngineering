package com.nie.sign;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nie.sign.aciivity.ClassDetailActivity;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.base.BaseActivity;
import com.nie.sign.fragment.HomeFragment;
import com.nie.sign.fragment.MyFragment;
import com.nie.sign.util.LoadingDialog;
import com.nie.sign.util.NavHelper;

public class MainActivity extends BaseActivity implements NavHelper.OnTabChangeListener {

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private NavHelper navHelper;
    private TextView tvTitle;
    private CoordinatorLayout coordinatorLayoutContent;


    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    private static final String DECODED_CONTENT_KEY = "codedContent";

    private static final int REQUEST_CODE_SCAN = 0x0000;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        tvTitle = findViewById(R.id.tv_title);
        coordinatorLayoutContent = findViewById(R.id.content);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);


        navHelper = new NavHelper(this, R.id.nav_host_fragment, getSupportFragmentManager(), this);
        navHelper.add(R.id.navigation_home, new NavHelper.Tab<>(HomeFragment.class, R.string.title_home))
                .add(R.id.navigation_my, new NavHelper.Tab<>(MyFragment.class, R.string.title_my));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //转接事件流到工具中
                return navHelper.performClickMenu(menuItem.getItemId());
            }
        });

        //默认加载首页
        Menu menu = bottomNavigationView.getMenu();
        menu.performIdentifierAction(R.id.navigation_home, 0);
    }

    @Override
    protected void initData() {
        LoadingDialog.showDialog(this);
        //高德地图定位
        mlocationClient = new AMapLocationClient(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mlocationClient.setLocationListener(new AMapLocationListener() {


            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                LoadingDialog.disDialog();
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        SystemApplication.amapLocation = amapLocation;

                        Toast.makeText(context, "定位成功!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "定位错误, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo(), Toast.LENGTH_SHORT).show();
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(Integer.MAX_VALUE);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        //启动定位
        mlocationClient.startLocation();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            exitAppDialog();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    public void exitAppDialog() {

        new AlertDialog.Builder(this)
                // .setIcon(android.R.drawable.ic_menu_info_detailsp)
                .setTitle("提示").setMessage("你确定要退出吗").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        }).show();

    }

    @Override
    public void onTabChange(NavHelper.Tab newTab, NavHelper.Tab oldTab) {
        tvTitle.setText(context.getString((Integer) newTab.extra));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String content = data.getStringExtra(DECODED_CONTENT_KEY);
        //班级信息加载

        Intent intent = new Intent(context, ClassDetailActivity.class);
        intent.putExtra("courseName", content);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mlocationClient.stopLocation();
    }
}
