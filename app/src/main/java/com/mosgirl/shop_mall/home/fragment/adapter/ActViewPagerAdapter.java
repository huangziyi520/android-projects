package com.mosgirl.shop_mall.home.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.mosgirl.shop_mall.home.bean.ResultBean;

import java.util.List;

import utils.Constants;

public class ActViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<ResultBean.ResultDTO.ActInfoDTO> actInfoDTOS;

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
        Glide.with(this.mContext).load(Constants.IMAGE_URL + actInfoDTOS.get(position).getIconUrl()).into(imageView);
        //添加到容器中
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
