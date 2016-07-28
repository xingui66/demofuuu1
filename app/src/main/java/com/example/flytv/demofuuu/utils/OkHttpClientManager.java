package com.example.flytv.demofuuu.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Flytv on 2016/7/27.
 */
public  class OkHttpClientManager  {
    //get/post请求方式
    private static OkHttpClientManager mInstance;
    private OkHttpClient mOkHttpClient;
    private OkHttpClientManager(){
        mOkHttpClient=new OkHttpClient();
    }

    public static OkHttpClientManager getInstance(){
        return mInstance;
    }

    /**
     * 同步get请求
     */
    private Response _getAsyn(String url)throws IOException{
        final Request request = new Request.Builder().url(url).build();
        Call call=mOkHttpClient.newCall(request);
        Response execute=call.execute();
        return execute;
    }
    //*************对外公布的方法************
    public static Response getAsyn(String url) throws IOException{

        return getInstance()._getAsyn(url);
    }

}
