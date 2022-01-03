package com.mosgirl.shop_mall.home.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mosgirl.shop_mall.R;
import com.mosgirl.shop_mall.home.bean.ResultBean;

import java.util.List;

import lombok.Data;
import utils.Constants;

/**
 * 频道适配器
 */
public class RecommendAdapter extends BaseAdapter {

    private Context context;
    private List<ResultBean.ResultDTO.RecommendInfoDTO> recommendInfoDTOS;

    public RecommendAdapter(Context context, List<ResultBean.ResultDTO.RecommendInfoDTO> recommendInfoDTOS) {
        this.context = context;
        this.recommendInfoDTOS = recommendInfoDTOS;
    }

    @Override
    public int getCount() {
        return this.recommendInfoDTOS.size();
    }

    @Override
    public Object getItem(int i) {
        return this.recommendInfoDTOS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(this.context, R.layout.item_recommend_grid_view, null);
            viewHolder.setTvName(view.findViewById(R.id.tv_name));
            viewHolder.setTvPrice(view.findViewById(R.id.tv_price));
            viewHolder.setIvRecommend(view.findViewById(R.id.iv_recommend));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //根据位置
        ResultBean.ResultDTO.RecommendInfoDTO recommendInfoDTO = this.recommendInfoDTOS.get(i);
        //加载图片
        Glide.with(context).load(Constants.IMAGE_URL + recommendInfoDTO.getFigure()).into(viewHolder.getIvRecommend());
        //设置文本
        viewHolder.getTvName().setText(recommendInfoDTO.getName());
        viewHolder.getTvPrice().setText(recommendInfoDTO.getCoverPrice());
        return view;
    }

    @Data
    static class ViewHolder {
        private ImageView ivRecommend;
        private TextView tvName,tvPrice;
    }
}
