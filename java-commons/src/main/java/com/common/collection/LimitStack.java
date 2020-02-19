package com.common.collection;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @date:2020/2/9 19:11
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class LimitStack<E> implements Iterable<E> {

    private Node<E> top;

    /**
     * 负责移除
     */

    private int size;


    private int len = 0;

    /**
     * 限制大小
     */
    public LimitStack(int size) {
        this.size = size;
    }

    public boolean push(E e) {
        // fill
        if (len == size) {
            return false;
        }
        // null , top . tail 初始化
        if (top == null) {
            top = new Node<>(e);
            len++;
            return true;
        }
        // 插入
        Node<E> node = new Node<>(e);
        top.pre = node;
        node.next = top;
        top = node;
        len++;
        return true;
    }


    /**
     * 出栈
     */
    public E pop() {
        if (top == null) {
            return null;
        }
        // top
        if (top.next == null) {
            E result = top.v;
            // 释放
            top.v = null;
            top = null;
            size--;
            return result;
        }

        // 需要移除的节点
        Node<E> rm = top;

        top = top.next;
        top.pre = null;

        // 获取值
        E e = rm.v;
        // 清空对象引用
        rm.v = null;
        rm.next = null;

        size--;
        return e;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> first = top;

            @Override
            public boolean hasNext() {
                return first != null;
            }

            @Override
            public E next() {
                Node<E> next = first;
                first = first.next;
                return next.v;
            }
        };
    }


    private static class Node<V> {
        Node<V> pre;
        Node<V> next;
        V v;

        Node() {
        }

        Node(V v) {
            this.v = v;
        }
    }


    @Override
    public void forEach(Consumer<? super E> action) {
        for (E e : this) {
            action.accept(e);
        }
    }

    public static void main(String[] args) {
        LimitStack<Integer> stack = new LimitStack<>(3);

        stack.push(1);
        stack.push(2);
        stack.push(3);


        System.out.println("stack.push(4) = " + stack.push(4));


//        Iterator<Integer> iterator = stack.iterator();
//
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//
//        for (Integer integer : stack) {
//            System.out.println(integer);
//        }
    }
}
