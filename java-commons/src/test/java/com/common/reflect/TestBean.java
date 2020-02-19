package com.common.reflect;

import sun.misc.Unsafe;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * TODO
 *
 * @date:2020/2/4 17:50
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class TestBean {
    public TestBean() {
    }

    private String name;

    public TestBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "name='" + name + '\'' +
                '}';
    }


    public static void main(String[] args) {
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();



        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
    }
}
