package com.mosgirl.shop_mall.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mosgirl.shop_mall.R;
import com.mosgirl.shop_mall.home.bean.GoodsBean;

import utils.Constants;

public class GoodsInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = GoodsInfoActivity.class.getSimpleName();
    private static final String GOODS_BEAN = "goodsBean";

    private Context context;
    private ImageButton ibGoodInfoBack, ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName, tvGoodInfoDesc, tvGoodInfoPrice, tvGoodInfoStore,
            tvGoodInfoStyle, tvGoodInfoCallCenter, tvGoodInfoCollection, tvGoodInfoShpCar, tvMoreLayoutSharding, tvMoreLayoutSearch, tvMoreLayoutHomePage;
    private WebView wbGoodInfoMore;
    private Button btGoodInfoAddShopCar;
    private LinearLayout llGoodsInfoMoreLayout;
    private int isVisible = View.GONE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_goods_info);
        findViews();
        GoodsBean goodsBean = (GoodsBean) getIntent().getSerializableExtra(GOODS_BEAN);
        if (goodsBean != null) {
            setDataForView(goodsBean);
        }
    }

    /**
     * 设置数据
     *
     * @param goodsBean
     */
    private void setDataForView(GoodsBean goodsBean) {

        //设置图片
        Glide.with(this).load(Constants.IMAGE_URL + goodsBean.getFigure()).into(ivGoodInfoImage);
        ivGoodInfoImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        tvGoodInfoName.setText(goodsBean.getName());
        tvGoodInfoPrice.setText("￥" + goodsBean.getCoverPrice());
        setWebViewData(goodsBean.getProductId());
    }

    private void setWebViewData(String productId) {
        if (productId != null) {
            this.wbGoodInfoMore.loadUrl("https://www.baidu.com/");
            WebSettings wbGoodInfoMoreSettings = this.wbGoodInfoMore.getSettings();
            wbGoodInfoMoreSettings.setUseWideViewPort(true);//支持双击页面变大 变小
            wbGoodInfoMoreSettings.setJavaScriptEnabled(true);//设置支持JavaScript
            wbGoodInfoMoreSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//设置优先使用缓存

            this.wbGoodInfoMore.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                    }
                    return true;
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }
    }

    private void findViews() {
        ibGoodInfoBack = findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = findViewById(R.id.tv_good_info_style);
        wbGoodInfoMore = findViewById(R.id.wb_good_info_more);
        tvGoodInfoCallCenter = findViewById(R.id.tv_good_info_call_center);
        tvGoodInfoCollection = findViewById(R.id.tv_good_info_collection);
        tvGoodInfoShpCar = findViewById(R.id.tv_good_info_shp_car);
        btGoodInfoAddShopCar = findViewById(R.id.bt_good_info_add_shop_car);
        tvMoreLayoutSharding = findViewById(R.id.tv_more_layout_sharding);
        tvMoreLayoutSearch = findViewById(R.id.tv_more_layout_search);
        tvMoreLayoutHomePage = findViewById(R.id.tv_more_layout_home_page);
        llGoodsInfoMoreLayout = findViewById(R.id.ll_goods_info_more_layout);
        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);
        ivGoodInfoImage.setOnClickListener(this);
        tvGoodInfoName.setOnClickListener(this);
        tvGoodInfoDesc.setOnClickListener(this);
        tvGoodInfoPrice.setOnClickListener(this);
        tvGoodInfoStore.setOnClickListener(this);
        tvGoodInfoStyle.setOnClickListener(this);
        wbGoodInfoMore.setOnClickListener(this);
        tvGoodInfoCallCenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoShpCar.setOnClickListener(this);
        btGoodInfoAddShopCar.setOnClickListener(this);
        tvMoreLayoutSharding.setOnClickListener(this);
        tvMoreLayoutSearch.setOnClickListener(this);
        tvMoreLayoutHomePage.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                Toast.makeText(this, "点击了返回", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_good_info_more:
                isVisible = isVisible == View.VISIBLE ? View.GONE : View.VISIBLE;
                llGoodsInfoMoreLayout.setVisibility(isVisible);
                break;
            case R.id.tv_good_info_name:
                Toast.makeText(this, "点击了商品名称", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_desc:
                Toast.makeText(this, "点击了商品详情", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_price:
                Toast.makeText(this, "点击了商品价格", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_store:
                Toast.makeText(this, "点击了商店名称", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_style:
                Toast.makeText(this, "点击了款式", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_call_center:
                Toast.makeText(this, "点击了联系客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_collection:
                Toast.makeText(this, "点击了收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_shp_car:
                Toast.makeText(this, "点击了购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_good_info_add_shop_car:
                Toast.makeText(this, "点击了添加购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_layout_sharding:
                Toast.makeText(this, "点击了分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_layout_search:
                Toast.makeText(this, "点击了搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_layout_home_page:
                Toast.makeText(this, "点击了首页", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
