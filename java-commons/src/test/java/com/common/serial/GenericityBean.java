package com.common.serial;

import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;

/**
 * 测试泛型支持
 *
 * @date:2020/1/6 18:00
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
@Message
public class GenericityBean<T> {

    @Index(0)
    private T value;

    public GenericityBean() {
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "GenericityBean{" +
                "value=" + value +
                '}';
    }
}
