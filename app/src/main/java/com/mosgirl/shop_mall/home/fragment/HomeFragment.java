package com.mosgirl.shop_mall.home.fragment;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mosgirl.shop_mall.R;
import com.mosgirl.shop_mall.base.BaseFragment;
import com.mosgirl.shop_mall.home.bean.ResultBean;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import utils.Constants;
import utils.JsonParseUtil;

/**
 * 主页面fragment
 */
public class HomeFragment extends BaseFragment {

    private final String TAG = HomeFragment.class.getSimpleName();
    private TextView tvTbSearch, tvTbMessage;
    private RecyclerView rvHome;
    private ImageView ivHfGoToTop;
    private ResultBean.ResultDTO resultDTO;
    private HomeFragmentAdapter adapter;
    private Context mContext;

    @Override
    public View initView() {
        Log.d(TAG, "HomeFragment页面的fragment的ui被初始化");
        View view = View.inflate(context, R.layout.home_fragment, null);
        tvTbSearch = view.findViewById(R.id.tv_tb_search);
        rvHome = view.findViewById(R.id.rv_hf);
        ivHfGoToTop = view.findViewById(R.id.iv_hf_go_to_top);
        tvTbMessage = view.findViewById(R.id.tv_tb_message);
        mContext = getContext();
        adapter = new HomeFragmentAdapter(mContext, resultDTO);
        rvHome.setAdapter(adapter);
        //设置布局管理者
        rvHome.setLayoutManager(new GridLayoutManager(mContext, 1));
        initListeners();
        return view;
    }

    private void initListeners() {
        ivHfGoToTop.setOnClickListener(view -> {
            //回到顶部
            rvHome.scrollToPosition(0);
        });
        //搜索的监听
        tvTbSearch.setOnClickListener(view -> {
            Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
        });
        //消息的监听
        tvTbMessage.setOnClickListener(view -> Toast.makeText(context, "进入消息中心", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void initData() {
        Log.d(TAG, "HomeFragment页面的数据初始化初始化");
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constants.HOME_URL).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i(TAG, TAG + "--get response failure", e);
            }

//            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, TAG + "--get response success,response:" + response);
                ResponseBody responseBody = response.body();
                assert responseBody != null;
                Log.d(TAG, TAG + "--get response success,responseBody:" + responseBody);
                ResultBean resultBean = JsonParseUtil.parseToBean(ResultBean.class, responseBody.string());
                resultDTO = resultBean.getResult();
                Log.d(TAG, TAG + "-解析成功result:" + resultDTO.getHotInfo().get(0).getName());
                if (resultDTO != null) {
//                    adapter = new HomeFragmentAdapter(mContext, resultDTO);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setResultDTO(resultDTO);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }

            }
        });
    }
}