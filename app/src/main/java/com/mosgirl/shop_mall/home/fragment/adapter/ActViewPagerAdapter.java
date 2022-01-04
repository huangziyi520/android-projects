package com.mosgirl.shop_mall.home.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.mosgirl.shop_mall.activity.GoodsInfoActivity;
import com.mosgirl.shop_mall.home.bean.GoodsBean;
import com.mosgirl.shop_mall.home.bean.ResultBean;

import java.util.List;

import utils.Constants;

public class ActViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<ResultBean.ResultDTO.ActInfoDTO> actInfoDTOS;
    private static final String GOODS_BEAN = "goodsBean";

    public ActViewPagerAdapter(Context mContext, List<ResultBean.ResultDTO.ActInfoDTO> actInfoDTOS) {
        this.mContext = mContext;
        this.actInfoDTOS = actInfoDTOS;
    }

    @Override
    public int getCount() {
        return actInfoDTOS.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * @param container 容器
     * @param position  对应页面的位置
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(this.mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setAdjustViewBounds(true);
        ResultBean.ResultDTO.ActInfoDTO actInfoDTO = actInfoDTOS.get(position);
        Glide.with(this.mContext).load(Constants.IMAGE_URL + actInfoDTO.getIconUrl()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setCoverPrice(actInfoDTO.getIconUrl());
                goodsBean.setName(actInfoDTO.getName());
                startGoodsInfoActivity(goodsBean);
            }
        });
        //添加到容器中
        container.addView(imageView);
        return imageView;
    }

    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(this.mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        this.mContext.startActivity(intent);
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
