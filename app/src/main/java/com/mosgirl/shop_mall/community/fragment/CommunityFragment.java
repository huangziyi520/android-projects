package com.mosgirl.shop_mall.community.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mosgirl.shop_mall.base.BaseFragment;

public class CommunityFragment extends BaseFragment {
    private final String TAG = CommunityFragment.class.getSimpleName();

    private TextView textView;

    @Override
    public View initView() {
        Log.d(TAG, "CommunityFragment页面的fragment的ui被初始化");
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    protected void initData() {
        Log.d(TAG, "CommunityFragment页面的fragment的数据被初始化");
        textView.setText("发现页面内容");
    }
}
