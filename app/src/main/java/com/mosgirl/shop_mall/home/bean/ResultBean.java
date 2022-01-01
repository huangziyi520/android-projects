package com.mosgirl.shop_mall.home.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResultBean implements Serializable {

    private int code;
    private String msg;
    private ResultDTO result;

    @NoArgsConstructor
    @Data
    public static class ResultDTO implements Serializable {
        private SeckillInfoDTO seckillInfo;
        private List<ActInfoDTO> actInfo;
        private List<BannerInfoDTO> bannerInfo;
        private List<ChannelInfoDTO> channelInfo;
        private List<HotInfoDTO> hotInfo;
        private List<RecommendInfoDTO> recommendInfo;

        @NoArgsConstructor
        @Data
        public static class SeckillInfoDTO implements Serializable {
            private String endTime;
            private String startTime;
            private List<ListDTO> list;

            @NoArgsConstructor
            @Data
            public static class ListDTO implements Serializable {
                private String coverPrice;
                private String figure;
                private String name;
                private String originPrice;
                private String productId;
            }
        }

        @NoArgsConstructor
        @Data
        public static class ActInfoDTO implements Serializable {
            private String iconUrl;
            private String name;
            private String url;
        }

        @NoArgsConstructor
        @Data
        public static class BannerInfoDTO implements Serializable {
            private String image;
            private int option;
            private int type;
            private ValueDTO value;

            @NoArgsConstructor
            @Data
            public static class ValueDTO implements Serializable {
                private String url;
            }
        }

        @NoArgsConstructor
        @Data
        public static class ChannelInfoDTO implements Serializable {
            private String channelName;
            private String image;
            private int option;
            private int type;
            private ValueDTOX value;

            @NoArgsConstructor
            @Data
            public static class ValueDTOX implements Serializable {
                private String channelId;
            }
        }

        @NoArgsConstructor
        @Data
        public static class HotInfoDTO implements Serializable {
            private String coverPrice;
            private String figure;
            private String name;
            private String productId;
        }

        @NoArgsConstructor
        @Data
        public static class RecommendInfoDTO implements Serializable {
            private String coverPrice;
            private String figure;
            private String name;
            private String productId;
        }
    }
}
