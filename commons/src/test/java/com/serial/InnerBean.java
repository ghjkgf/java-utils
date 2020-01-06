package com.serial;

import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;

/**
 * 测试类
 *
 * @date:2020/1/6 17:55
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
@Message
public class InnerBean {


    @Index(0)
    private String name;


    public InnerBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InnerBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
