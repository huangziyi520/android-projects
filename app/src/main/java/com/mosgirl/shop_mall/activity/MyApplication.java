package com.mosgirl.shop_mall.activity;

import android.app.Application;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initOkhttpClient();
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();

    }
}
