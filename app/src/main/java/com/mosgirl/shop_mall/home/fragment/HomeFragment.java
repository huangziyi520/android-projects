package com.mosgirl.shop_mall.home.fragment;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.mosgirl.shop_mall.R;
import com.mosgirl.shop_mall.base.BaseFragment;

/**
 * 主页面fragment
 */
public class HomeFragment extends BaseFragment {

    private final String TAG = HomeFragment.class.getSimpleName();
    private TextView tvTbSearch, tvTbMessage;
    private RecyclerView rvHf;
    private ImageView ivHfGoToTop;

    @Override
    public View initView() {
        Log.d(TAG, "HomeFragment页面的fragment的ui被初始化");
        View view = View.inflate(context, R.layout.home_fragment, null);
        tvTbSearch = view.findViewById(R.id.tv_tb_search);
        rvHf = view.findViewById(R.id.rv_hf);
        ivHfGoToTop = view.findViewById(R.id.iv_hf_go_to_top);
        tvTbMessage = view.findViewById(R.id.tv_tb_message);
        initListeners();
        return view;
    }

    private void initListeners() {
        ivHfGoToTop.setOnClickListener(view -> {
            //回到顶部
            rvHf.scrollToPosition(0);
        });
        //搜索的监听
        tvTbSearch.setOnClickListener(view -> {
            Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
        });
        //消息的监听
        tvTbMessage.setOnClickListener(view -> Toast.makeText(context, "进入消息中心", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void initData() {

    }
}