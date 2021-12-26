package com.mosgirl.shop_mall.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mosgirl.shop_mall.R;
import com.mosgirl.shop_mall.base.BaseFragment;
import com.mosgirl.shop_mall.community.fragment.CommunityFragment;
import com.mosgirl.shop_mall.home.fragment.HomeFragment;
import com.mosgirl.shop_mall.shappingcar.fragment.ShoppingCarFragment;
import com.mosgirl.shop_mall.type.fragment.TypeFragment;
import com.mosgirl.shop_mall.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<BaseFragment> fragments;
    private RadioGroup igGroup;
    private RadioButton ibHome, ibCommunity, ibShoppingCar, ibType, ibUser;
    private int cachedFragmentId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化view
        initView();
        //初始化fragment
        initFragment();
        //设置监听器
        setListener();
        //设置默认主页
        switchFragment(fragments.get(0));
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        igGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            int position;

            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.ib_home:
                        position = 0;
                        break;
                    case R.id.ib_community:
                        position = 1;
                        break;
                    case R.id.ib_type:
                        position = 2;
                        break;
                    case R.id.ib_shopping_car:
                        position = 3;
                        break;
                    case R.id.ib_user:
                        position = 4;
                        break;
                }
                if (cachedFragmentId != position) {
                    cachedFragmentId = position;
                    BaseFragment fragment = getFragment(position);
                    //替换布局
                    switchFragment(fragment);
                }
            }
        });
    }

    private void switchFragment(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, fragment);
        transaction.commit();
    }

    private BaseFragment getFragment(int position) {
        return fragments.get(position);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new TypeFragment());
        fragments.add(new ShoppingCarFragment());
        fragments.add(new UserFragment());
    }

    /**
     * 初始化view
     */
    private void initView() {
        igGroup = findViewById(R.id.ig_group);
        ibHome = findViewById(R.id.ib_home);
        ibCommunity = findViewById(R.id.ib_community);
        ibShoppingCar = findViewById(R.id.ib_shopping_car);
        ibType = findViewById(R.id.ib_type);
        ibUser = findViewById(R.id.ib_user);
    }
}
