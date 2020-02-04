package com.common.serial;


import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * 序列化与返学裂化
 *
 * @date:2019/9/27 13:16
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */

public class SerializationUtils {

    /**
     * 反序列化
     */
    public static <T> T getObject(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            throw new RuntimeException("SerializationUtils 反序列化异常");
        }
        try (ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes, 0, bytes.length);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream)) {
            return (T) objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException("SerializationUtils 反序列化异常");
        }
    }

    /**
     * @param bean
     */
    public static <T> byte[] getByte(T bean) {
        if (bean == null) {
            return null;
        }
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(bean);
            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("SerializationUtils 序列化异常");
        }
    }

}
