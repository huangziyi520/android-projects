package com.mosgirl.shop_mall.activity;

import android.app.Application;

public class MyApplication extends Application {
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        initOkhttpClient();
    }

    private void initOkhttpClient() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS).build();
//        OkHttpUt
    }
}
