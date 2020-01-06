package com.serial;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * Fast JSON 工具
 *
 * JsonUtil.parseObject(json, new TypeReference<List<Integer>>() {});
 *
 *
 * @date:2019/11/13 11:58
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */

public final class JsonUtil {
    


    public static <T> T parseObject(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type);
    }



    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }


    public static Object parseObject(String json, String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        return JSON.parseObject(json, clazz);
    }

}