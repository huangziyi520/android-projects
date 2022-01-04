package com.mosgirl.shop_mall.home.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 商品对象
 */
@Data
public class GoodsBean implements Serializable {

    //价格
    private String CoverPrice;
    //图片
    private String figure;
    //名称
    private String name;
    //产品id
    private String productId;
}
