package com.common.module;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

/**
 * Tuple
 *
 * @date:2020/1/27 16:02
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public final class Tuple2<T> implements Serializable {

    private static final long serialVersionUID = 7143211771680961719L;

    private T _1;
    private T _2;

    public T get_1() {
        return _1;
    }

    public void set_1(T _1) {
        this._1 = _1;
    }

    public T get_2() {
        return _2;
    }

    public void set_2(T _2) {
        this._2 = _2;
    }

    public Tuple2() {
    }

    public Tuple2(T _1, T _2) {
        this._1 = _1;
        this._2 = _2;
    }

    @Override
    public int hashCode() {
        Random random = new Random();
        return random.nextInt(10000) * 16;
    }

    @Override
    public String toString() {
        return "Tuple2{" +
                "_1=" + _1 +
                ", _2=" + _2 +
                '}';
    }

}
