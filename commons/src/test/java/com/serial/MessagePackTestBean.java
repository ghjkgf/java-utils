package com.serial;

import org.msgpack.annotation.Index;
import org.msgpack.annotation.Message;

import java.util.Arrays;
import java.util.Collection;

/**
 * 其中 Bean 必须有一个无参的构造器
 * 他支持基本大部分java对象, 对于集合是支持的
 * 其中对于自己实现的Bean也需要实现Message注解和其他
 *
 *
 * 对于泛型不支持 , 解决方法类似于JSON那种解决方式.
 *
 *
 * @date:2020/1/6 17:45
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
@Message
public class MessagePackTestBean {

    /**
     * 必须有一个无参 构造器
     */
    public MessagePackTestBean() {
    }


    /**
     * Index 从0开始标记
     */
    @Index(0)
    private String name;

    @Index(1)
    private Integer age;

    @Index(2)
    private long timestamp;

    @Index(3)
    private byte[] body;

    @Index(4)
    private Collection<String> hobby;

    @Index(5)
    private InnerBean bean;



    public InnerBean getBean() {
        return bean;
    }

    public void setBean(InnerBean bean) {
        this.bean = bean;
    }


    public Collection<String> getHobby() {
        return hobby;
    }

    public void setHobby(Collection<String> hobby) {
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }


    @Override
    public String toString() {
        return "MessagePackTestBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", timestamp=" + timestamp +
                ", body=" + Arrays.toString(body) +
                ", hobby=" + hobby +
                ", bean=" + bean +
                '}';
    }
}
