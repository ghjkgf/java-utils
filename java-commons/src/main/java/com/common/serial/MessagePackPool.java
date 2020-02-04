package com.common.serial;

import org.msgpack.MessagePack;


/**
 * MessagePackPool  线程安全的对象
 * <p>
 * <p>
 * {@link org.msgpack.annotation.Message}
 * <p>
 * {@link org.msgpack.annotation.Index}
 *
 * <a>https://msgpack.org/</a> 官网
 * <p>
 * <p>
 * 测试对象在 test包下面. 想学习的可以看一下
 *
 * @date:2019/11/8 16:39
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */

public final class MessagePackPool {
    private MessagePackPool() {
    }

    private static final ThreadLocal<MessagePack> packs = ThreadLocal.withInitial(MessagePack::new);

    /**
     * 获取一个 message pack
     *
     * @return MessagePack
     */
    public static MessagePack getPack() {
        return packs.get();
    }


    /**
     * 移除对象 ,防止内存泄漏
     */
    public static void removePack() {
        if (null != packs.get()) {
            packs.remove();
        }
    }

}
