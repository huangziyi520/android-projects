package com.mosgirl.shop_mall.home.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mosgirl.shop_mall.R;
import com.mosgirl.shop_mall.home.bean.ResultBean;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import utils.Constants;

@Data
public class HomeFragmentAdapter extends RecyclerView.Adapter {

    private final String TAG = HomeFragmentAdapter.class.getSimpleName();
    /**
     * 广告条幅类型
     */
    public static final int BANNER = 0;
    /**
     * 频道类型
     */
    public static final int CHANNEL = 1;

    /**
     * 活动类型
     */
    public static final int ACT = 2;

    /**
     * 秒杀类型
     */
    public static final int SECKILL = 3;

    /**
     * 推荐类型
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;

    /**
     * 初始化布局
     */
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    //resultBean
    private ResultBean.ResultDTO resultDTO;

    private int currentType = BANNER;

    public HomeFragmentAdapter(Context mContext, ResultBean.ResultDTO resultDTO) {
        this.mContext = mContext;
        this.resultDTO = resultDTO;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 相当于getView 创建viewHolder部分代码
     * 创建viewHolder
     *
     * @param parent
     * @param viewType 当前类型
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, mLayoutInflater.inflate(R.layout.banner_viewpager, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            if (resultDTO != null) {
                bannerViewHolder.setData(resultDTO.getBannerInfo());
            }
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private Banner banner;

        BannerViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            this.banner = itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBean.ResultDTO.BannerInfoDTO> bannerInfo) {
            //设置Banner的数据
            //得到图片地址
            List<String> imagesUrl = new ArrayList<>();
            for (int i = 0; i < bannerInfo.size(); i++) {
                String imageUrl = bannerInfo.get(i).getImage();
                imagesUrl.add(imageUrl);

            }
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片-Glide
//                    Log.d(TAG, TAG + "--联网请求图片-url:" + Constants.IMAGE_URL + url);
                    Glide.with(mContext).load(Constants.IMAGE_URL + url).into(view);
                }
            });
        }
    }
}
