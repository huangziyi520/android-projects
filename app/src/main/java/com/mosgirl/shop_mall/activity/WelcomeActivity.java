package com.mosgirl.shop_mall.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mosgirl.shop_mall.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        final Context context = this;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //在主线程中执行
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

}
