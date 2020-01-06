package com.test;

import java.lang.reflect.Array;

/**
 * TODO
 *
 * @date:2019/12/29 13:20
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class Demo {


    public static void main(String[] args) throws Exception {
//        ClassLoader.getSystemClassLoader().loadClass("com.module.Pair");


        Object o = Array.newInstance(String.class, 10);

        String[] arr = (String[]) o;


        System.out.println(o);

//        Class.forName("com.module.Pair");
    }
}
