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
public class ChannelAdapter extends BaseAdapter {

    private Context context;
    private List<ResultBean.ResultDTO.ChannelInfoDTO> channelInfoDTOS;

    public ChannelAdapter(Context context, List<ResultBean.ResultDTO.ChannelInfoDTO> channelInfoDTOS) {

        this.context = context;
        this.channelInfoDTOS = channelInfoDTOS;
    }

    @Override
    public int getCount() {
        return channelInfoDTOS.size();
    }

    @Override
    public Object getItem(int i) {
        return channelInfoDTOS.get(i);
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
            view = View.inflate(this.context, R.layout.item_channel, null);
            viewHolder.setIgChannel(view.findViewById(R.id.iv_channel));
            viewHolder.setTvChannel(view.findViewById(R.id.tv_channel));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //根据位置
        ResultBean.ResultDTO.ChannelInfoDTO channelInfoDTO = channelInfoDTOS.get(i);
        //加载图片
        Glide.with(context).load(Constants.IMAGE_URL + channelInfoDTO.getImage()).into(viewHolder.getIgChannel());
        //设置文本
        viewHolder.getTvChannel().setText(channelInfoDTO.getChannelName());
//        viewHolder.getIgChannel().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "频道点击了" + i, Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    @Data
    static class ViewHolder {
        private ImageView igChannel;
        private TextView tvChannel;
    }
}
