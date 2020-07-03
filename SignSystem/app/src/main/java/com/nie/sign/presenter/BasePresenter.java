package com.nie.sign.presenter;

import com.google.gson.Gson;
import com.nie.sign.presenter.net.ResponseInfoAPI;
import com.nie.sign.presenter.net.bean.ResponseInfo;
import com.nie.sign.util.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HASEE on 2017/1/9.
 */

public abstract class BasePresenter {

    public ResponseInfoAPI responseInfoAPI;


    //网络请求
    public BasePresenter() {
        //1.初始化retrofit对象
        //2.指定了访问服务器工程的主机名
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Constant.BASEURL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        //3.指定具体的网络请求实体对象
        //发送一个网络请求(完整的链接地址,请求方式(GET POST),请求过程中参数,请求的响应结果)
        responseInfoAPI = retrofit.create(ResponseInfoAPI.class);
    }



    class CallBackAdapter implements Callback<ResponseInfo> {
        @Override
        public void onResponse(Call<ResponseInfo> call, Response<ResponseInfo> response) {

            //请求成功,获取服务器返回的所有的json串
            ResponseInfo body = response.body();

            if (body != null && (body.getStatus() == 0)) {

                //请求成功
                String json = new Gson().toJson(body.getData());
                //抽象的解析json方法,让子类对json做具体的解析过程
                parseJson(json);

            } else {
                //请求失败
                String errorMessage = "";
                if (body != null) {
                    errorMessage = body.getMsg();
                } else {
                    errorMessage = "请求错误！状态码：" + response.raw().code();
                }

                onFailure(call, new RuntimeException(errorMessage));
            }
        }


        @Override
        public void onFailure(Call<ResponseInfo> call, Throwable t) {
            t.printStackTrace();
            //请求失败
            String message = t.getMessage();
            showError(message);
        }
    }

    protected abstract void showError(String message);

    protected abstract void parseJson(String json);
}
