package utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class JsonParseUtil {
    private static final String TAG = JsonParseUtil.class.getSimpleName();

    public static <T> List<T> parseToBeanArray(Class<T> type, String str) {
        return JSON.parseArray(str, type);
    }


    public static <T> T parseToBean(Class<T> type, String str) {
        return JSON.parseObject(str, type);
    }
}
