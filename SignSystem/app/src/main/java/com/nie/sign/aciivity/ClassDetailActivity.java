package com.nie.sign.aciivity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.nie.sign.R;
import com.nie.sign.application.SystemApplication;
import com.nie.sign.base.BaseActivity;
import com.nie.sign.presenter.CourseInfoDetailPresenter;

import java.io.UnsupportedEncodingException;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class ClassDetailActivity extends BaseActivity {

    public RelativeLayout rl;
    public TextView tvTitle;
    public ImageView ivBack;
    public TextView tvClassName;
    public TextView tvClassTea;
    public TextView tvClassDate;
    public TextView tvClassUid;
    public TextView tvClassStuNum;
    public ImageView ivImg;
    public Button btnAddClass;
    public LinearLayout llContent;
    public TextView tvSignStatus;
    public TextView tvSignStatusDetail;
    public Button btnSignLaunch;
    public ListView lvStuData;
    public String courseName;
    private CourseInfoDetailPresenter courseInfoDetailPresenter;


    @Override
    protected int initLayout() {
        return R.layout.activity_class_detail;
    }

    @Override
    protected void initView() {

        rl = (RelativeLayout) findViewById(R.id.rl);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvClassName = (TextView) findViewById(R.id.tv_class_name);
        tvClassTea = (TextView) findViewById(R.id.tv_class_tea);
        tvClassDate = (TextView) findViewById(R.id.tv_class_date);
        tvClassUid = (TextView) findViewById(R.id.tv_class_uid);
        tvClassStuNum = (TextView) findViewById(R.id.tv_class_stu_num);
        ivImg = (ImageView) findViewById(R.id.iv_img);
        btnAddClass = (Button) findViewById(R.id.btn_add_class);
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        tvSignStatus = (TextView) findViewById(R.id.tv_sign_status);
        tvSignStatusDetail = (TextView) findViewById(R.id.tv_sign_status_detail);
        btnSignLaunch = (Button) findViewById(R.id.btn_sign_launch);
        lvStuData = (ListView) findViewById(R.id.lv_stu_data);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

        //生成二维码
        courseName = getIntent().getStringExtra("courseName");
        Bitmap bitmap = null;
        try {
            bitmap = encodeAsBitmap(new String(courseName.getBytes("UTF-8"), "ISO-8859-1"), dip2px(100), dip2px(100));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ivImg.setImageBitmap(bitmap);

        //班级信息加载
        if(courseInfoDetailPresenter==null){
            courseInfoDetailPresenter = new CourseInfoDetailPresenter(this);
        }

        courseInfoDetailPresenter.getCourseInfoDetail();
    }



    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 生成二维码
     *
     * @param str
     * @param width
     * @param height
     * @return
     */
    Bitmap encodeAsBitmap(String str, int width, int height) {
        BitMatrix result = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }


        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, w, h);

        return bitmap;
    }
}
