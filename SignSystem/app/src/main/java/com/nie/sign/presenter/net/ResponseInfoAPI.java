package com.nie.sign.presenter.net;

import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by HASEE on 2017/1/9.
 */
public interface ResponseInfoAPI {

    //登录接口
    @POST(Constant.LOGIN)
    Call<ResponseInfo> getLoginInfo(@Query("account") String account, @Query("password") String password);


    //注册-邮箱验证
    @GET(Constant.SNED_EMAIL_CODE)
    Call<ResponseInfo> sendEmailCode(@Query("receiver") String email, @Query("type") String type);

    //注册-用户注册
    @POST(Constant.REGISTER)
    Call<ResponseInfo> register(@Query("account") String account,
                                @Query("pwd") String password,
                                @Query("email") String email,
                                @Query("code") String code);

    //忘记密码-邮箱验证
    @GET(Constant.SNED_FORGET_EMAIL_CODE)
    Call<ResponseInfo> sendForgetEmailCode(@Query("receiver") String email, @Query("type") String type);

    //忘记密码-（修改密码）
    @POST(Constant.CHAGE_PASSWORD)
    Call<ResponseInfo> changePassword(@Query("account") String account,
                                      @Query("password") String password,
                                      @Query("email") String email,
                                      @Query("code") String code);

    //获取用户信息
    @GET(Constant.GET_USER_INFO)
    Call<ResponseInfo> getUserInfo(@Header("token") String token);


    //查询所有课程
    @GET(Constant.GET_ALL_COURSE)
    Call<ResponseInfo> getAllCourseInfo(@Header("token") String token);

    //创建班课
    @POST(Constant.ADD_CLASS)
    Call<ResponseInfo> addClass(@Header("token") String token, @Query("courseName") String className, @Query("account") String account);

    //返回班课信息
    @GET(Constant.GET_COURSE_DETAIL)
    Call<ResponseInfo> getCourseInfoDetail(@Header("token") String token, @Query("courseName") String courseName);

    //加入班课
    @POST(Constant.ADD_COURSE)
    Call<ResponseInfo> addCourse(@Header("token") String token, @Query("courseName") String courseName, @Query("account") String account);

    //结束签到
    @POST(Constant.END_SIGN)
    Call<ResponseInfo> endSign(@Header("token") String token, @Query("courseName") String courseName, @Query("account") String username);

    //发起签到
    @POST(Constant.START_SIGN)
    Call<ResponseInfo> startSign(@Header("token") String token, @Query("courseName") String courseName, @Query("account") String username, @Query("lng") String longitude, @Query("lat") String latitude);

    //签到
    @POST(Constant.SIGN)
    Call<ResponseInfo> sign(@Header("token") String token, @Query("courseName") String courseName, @Query("account") String username, @Query("lng") String longitude, @Query("lat") String latitude);

    //查询用户课程
    @GET(Constant.GET_MY_COURSE)
    Call<ResponseInfo> getMyCourseInfo(@Header("token") String token, @Query("account") String username);
}
