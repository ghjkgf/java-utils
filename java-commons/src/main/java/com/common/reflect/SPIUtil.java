package com.common.reflect;


import java.util.*;

/**
 * SPI 拓展机制
 *
 * @date:2019/12/25 10:31
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public final class SPIUtil {


    /**
     * 获取默认值
     */
    public static <T> T loadFirstInstanceOrDefault(Class<T> clazz, Class<? extends T> defaultClass) {
        try {
            ServiceLoader<T> serviceLoader = ServiceLoader.load(clazz, Thread.currentThread().getContextClassLoader());
            for (T instance : serviceLoader) {
                if (instance != defaultClass) {
                    return instance;
                }
            }
            return defaultClass.newInstance();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
}
