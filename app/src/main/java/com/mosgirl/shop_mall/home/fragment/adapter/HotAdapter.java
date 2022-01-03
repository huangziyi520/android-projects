package com.mosgirl.shop_mall.home.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mosgirl.shop_mall.R;
import com.mosgirl.shop_mall.home.bean.ResultBean;

import java.util.List;

import utils.Constants;

public class HotAdapter extends BaseAdapter {


    private Context mContext;
    private List<ResultBean.ResultDTO.HotInfoDTO> hotInfoDTOS;

    public HotAdapter(Context mContext, List<ResultBean.ResultDTO.HotInfoDTO> hotInfoDTOS) {
        this.mContext = mContext;
        this.hotInfoDTOS = hotInfoDTOS;
    }

    @Override
    public int getCount() {
        return this.hotInfoDTOS.size();
    }

    @Override
    public Object getItem(int i) {
        return this.hotInfoDTOS.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        ResultBean.ResultDTO.HotInfoDTO hotInfoDTO = this.hotInfoDTOS.get(i);
        if (view == null) {
            view = View.inflate(this.mContext,R.layout.item_hot_grid_view,null);
            viewHolder = new ViewHolder(view, this.mContext);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(this.mContext).load(Constants.IMAGE_URL + hotInfoDTO.getFigure()).into(viewHolder.ivHot);
        viewHolder.tvName.setText(hotInfoDTO.getName());
        viewHolder.tvPrice.setText(hotInfoDTO.getCoverPrice());
        view.setTag(viewHolder);
        return view;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private ImageView ivHot;
        private TextView tvName, tvPrice;

        public ViewHolder(@NonNull View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            ivHot = itemView.findViewById(R.id.iv_hot);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
