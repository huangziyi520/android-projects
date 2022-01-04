package com.mosgirl.shop_mall.home.fragment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.mosgirl.shop_mall.R;
import com.mosgirl.shop_mall.activity.GoodsInfoActivity;
import com.mosgirl.shop_mall.home.bean.GoodsBean;
import com.mosgirl.shop_mall.home.bean.ResultBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnLoadImageListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import utils.Constants;

@Data
@EqualsAndHashCode(callSuper = false)
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
    private static final String GOODS_BEAN = "goodsBean";

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
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, mLayoutInflater.inflate(R.layout.channel_item, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, mLayoutInflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, mLayoutInflater.inflate(R.layout.seckill_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, mLayoutInflater.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(mContext, mLayoutInflater.inflate(R.layout.hot_item, null));
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
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            if (resultDTO != null) {
                channelViewHolder.setData(resultDTO.getChannelInfo());
            }
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            if (resultDTO != null) {
                actViewHolder.setData(resultDTO.getActInfo());
            }
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            if (resultDTO != null) {
                seckillViewHolder.setData(resultDTO.getSeckillInfo());
            }
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            if (resultDTO != null) {
                recommendViewHolder.setData(resultDTO.getRecommendInfo());
            }
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            if (resultDTO != null) {
                hotViewHolder.setData(resultDTO.getHotInfo());
            }
        }
    }

    @Override
    public int getItemCount() {
        return 6;
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

    //广告轮播图
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
            //设置循环指示点
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //设置手风琴效果
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片-Glide
//                    Log.d(TAG, TAG + "--联网请求图片-url:" + Constants.IMAGE_URL + url);
                    Glide.with(mContext).load(Constants.IMAGE_URL + url).into(view);
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            GoodsBean goodsBean = new GoodsBean();
                            goodsBean.setFigure(String.valueOf(url));
                            startGoodsInfoActivity(goodsBean);
                        }
                    });
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private GridView gcChannel;
        private ChannelAdapter channelAdapter;

        public ChannelViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            this.gcChannel = itemView.findViewById(R.id.gv_channel);
        }

        public void setData(List<ResultBean.ResultDTO.ChannelInfoDTO> channelInfo) {
            //得到数据 设置GridView适配器
            channelAdapter = new ChannelAdapter(context, channelInfo);
            gcChannel.setAdapter(channelAdapter);
            if (channelInfo.size() > 0) {
                gcChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ResultBean.ResultDTO.ChannelInfoDTO channelInfoDTO = channelInfo.get(i);
                        GoodsBean goodsBean = new GoodsBean();
//                        goodsBean.setCoverPrice(channelInfoDTO.getCoverPrice());
                        goodsBean.setFigure(channelInfoDTO.getImage());
//                        goodsBean.setProductId(channelInfoDTO.getOption());
                        goodsBean.setName(channelInfoDTO.getChannelName());
                        Toast.makeText(mContext, "goodsBean=" + goodsBean.toString(), Toast.LENGTH_SHORT).show();
                        startGoodsInfoActivity(goodsBean);
                    }
                });
            }
        }
    }

    static class ActViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private ViewPager actViewPager;
        private ActViewPagerAdapter actViewPagerAdapter;

        public ActViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            this.actViewPager = itemView.findViewById(R.id.act_viewpager);
//            this.actViewPager.setOffscreenPageLimit(3);//>=3
            //决定动画效果
//            this.actViewPager.setPageTransformer(true, new ScaleInTransformer());
        }

        public void setData(List<ResultBean.ResultDTO.ActInfoDTO> actInfoDTOS) {
            if (actInfoDTOS != null) {
                actViewPagerAdapter = new ActViewPagerAdapter(this.context, actInfoDTOS);
                actViewPager.setAdapter(actViewPagerAdapter);
            }
        }
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {

        /**
         * 设计倒计时
         */
        @SuppressLint("HandlerLeak")
        private final Handler handler = new Handler() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
//                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
                Date date = new Date(dt);
                String timeStr = simpleDateFormat.format(date);
                tvTimeSeckill.setText(timeStr);
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0, 1000);
                dt = dt - 1000;
                if (dt <= 0) {
                    tvTimeSeckill.setText("00:00:00");
                    handler.removeCallbacksAndMessages(null);
                }

            }
        };
        private Context context;
        private TextView tvTimeSeckill, tvMoreSeckill;
        private RecyclerView rvSeckill;
        private SeckillAdapter seckillAdapter;
        private ResultBean.ResultDTO.SeckillInfoDTO seckillInfo;
        private int dt;
        private boolean flag = false;

        public SeckillViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            this.tvTimeSeckill = itemView.findViewById(R.id.tv_time_seckill);
            this.tvMoreSeckill = itemView.findViewById(R.id.tv_more_seckill);
            this.rvSeckill = itemView.findViewById(R.id.rv_seckill);
        }

        public void setData(ResultBean.ResultDTO.SeckillInfoDTO seckillInfo) {
            if (seckillInfo != null) {
                this.seckillInfo = seckillInfo;
                seckillAdapter = new SeckillAdapter(this.context, seckillInfo.getList());
//                seckillAdapter.setOnSeckillRecycleView(new SeckillAdapter.OnSeckillRecycleView() {
//                    @Override
//                    public void OnItemClick(int position) {
//                        Toast.makeText(context, "秒杀" + position, Toast.LENGTH_SHORT).show();
//                    }
//                });
                rvSeckill.setAdapter(seckillAdapter);
                rvSeckill.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false));
                if (!flag) {
                    dt = Integer.parseInt(seckillInfo.getEndTime()) - Integer.parseInt(seckillInfo.getStartTime());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:SS");
                    String timeStr = simpleDateFormat.format(new Date(dt));
                    tvTimeSeckill.setText(timeStr);
                    handler.sendEmptyMessage(0);
                    flag = true;
                }
            }
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tvMoreRecommend;
        private GridView gvRecommend;
        private RecommendAdapter recommendAdapter;

        public RecommendViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            tvMoreRecommend = view.findViewById(R.id.tv_more_recommend);
            gvRecommend = view.findViewById(R.id.gv_recommend);
        }


        public void setData(List<ResultBean.ResultDTO.RecommendInfoDTO> recommendInfoDTOS) {
            if (recommendInfoDTOS != null) {
                recommendAdapter = new RecommendAdapter(this.mContext, recommendInfoDTOS);
                gvRecommend.setAdapter(recommendAdapter);
                this.gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ResultBean.ResultDTO.RecommendInfoDTO recommendInfoDTO = recommendInfoDTOS.get(i);
                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setCoverPrice(recommendInfoDTO.getCoverPrice());
                        goodsBean.setFigure(recommendInfoDTO.getFigure());
                        goodsBean.setProductId(recommendInfoDTO.getProductId());
                        goodsBean.setName(recommendInfoDTO.getName());
                        startGoodsInfoActivity(goodsBean);
                    }
                });
            }
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView tvMoreHot;
        private GridView gvHot;
        private HotAdapter hotAdapter;

        public HotViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            tvMoreHot = view.findViewById(R.id.tv_more_hot);
            gvHot = view.findViewById(R.id.gv_hot);
        }

        public void setData(List<ResultBean.ResultDTO.HotInfoDTO> hotInfoDTOS) {
            if (hotInfoDTOS != null) {
                hotAdapter = new HotAdapter(this.mContext, hotInfoDTOS);
                gvHot.setAdapter(hotAdapter);
                this.gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ResultBean.ResultDTO.HotInfoDTO hotInfoDTO = hotInfoDTOS.get(i);
                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setCoverPrice(hotInfoDTO.getCoverPrice());
                        goodsBean.setFigure(hotInfoDTO.getFigure());
                        goodsBean.setProductId(hotInfoDTO.getProductId());
                        goodsBean.setName(hotInfoDTO.getName());
                        startGoodsInfoActivity(goodsBean);
                    }
                });
            }
        }
    }

    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(this.mContext, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN, goodsBean);
        this.mContext.startActivity(intent);
    }
}
