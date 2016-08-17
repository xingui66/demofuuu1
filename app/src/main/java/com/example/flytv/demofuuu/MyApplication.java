package com.example.flytv.demofuuu;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Flytv on 2016/7/27.
 */
public class MyApplication extends Application {
    public static RequestQueue queue;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Context
        context = getApplicationContext();
        //初始化Handler
        queue= Volley.newRequestQueue(getApplicationContext());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

       // OkHttpUtils.initClient(okHttpClient);
    }


    //获取全局的请求队列
    public static RequestQueue getHttpQueue(){
        return queue;
    }
    public static Context getContext() {
        return context;
    }

}
