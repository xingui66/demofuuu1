package com.example.flytv.demofuuu;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Flytv on 2016/7/27.
 */
public class MyApplication extends Application {
    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue= Volley.newRequestQueue(getApplicationContext());

    }


    //获取全局的请求队列
    public static RequestQueue getHttpQueue(){
        return queue;
    }


}
