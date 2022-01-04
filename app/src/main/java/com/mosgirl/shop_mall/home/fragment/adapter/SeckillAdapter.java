package com.mosgirl.shop_mall.home.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mosgirl.shop_mall.R;
import com.mosgirl.shop_mall.activity.GoodsInfoActivity;
import com.mosgirl.shop_mall.home.bean.GoodsBean;
import com.mosgirl.shop_mall.home.bean.ResultBean;

import java.util.List;

import utils.Constants;

/**
 * 秒杀适配器
 */
public class SeckillAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ResultBean.ResultDTO.SeckillInfoDTO.ListDTO> list;
    private OnSeckillRecycleView onSeckillRecycleView;
    private static final String GOODS_BEAN = "goodsBean";


    public SeckillAdapter(Context context, List<ResultBean.ResultDTO.SeckillInfoDTO.ListDTO> list) {
        this.mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill, null);
        return new ViewHolder(this.mContext, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResultBean.ResultDTO.SeckillInfoDTO.ListDTO listDTO = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(this.mContext).load(Constants.IMAGE_URL + listDTO.getFigure()).into(viewHolder.ivFigure);

        viewHolder.ivFigure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "秒杀:", Toast.LENGTH_SHORT).show();
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setCoverPrice(listDTO.getCoverPrice());
                goodsBean.setFigure(listDTO.getFigure());
                goodsBean.setProductId(listDTO.getProductId());
                goodsBean.setName(listDTO.getName());
                startGoodsInfoActivity(goodsBean);
            }
        });

        viewHolder.tvOriginPrice.setText(listDTO.getOriginPrice());
        viewHolder.tvCoverPrice.setText(listDTO.getCoverPrice());
    }

    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(this.mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN, goodsBean);
        this.mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public Context context;
        public ImageView ivFigure;
        public TextView tvCoverPrice, tvOriginPrice;

        public ViewHolder(Context mContext, View view) {
            super(view);
            this.context = mContext;
            ivFigure = view.findViewById(R.id.iv_figure);
            tvCoverPrice = view.findViewById(R.id.tv_cover_price);
            tvOriginPrice = view.findViewById(R.id.tv_origin_price);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "秒杀的位置:" + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    if (onSeckillRecycleView != null) {
                        onSeckillRecycleView.OnItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    /**
     * 设置item的监听
     *
     * @param onSeckillRecycleView
     */
    public void setOnSeckillRecycleView(OnSeckillRecycleView onSeckillRecycleView) {
        this.onSeckillRecycleView = onSeckillRecycleView;
    }

    /**
     * 监听器
     */
    public interface OnSeckillRecycleView {
        /**
         * 当某条被点击的时候回调
         *
         * @param position
         */
        void OnItemClick(int position);
    }
}
