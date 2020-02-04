package com.common.collection;

import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCacheTest {


    @Test
    public void test() {
        ConcurrentHashSet<String> set = new ConcurrentHashSet<>();

        String str = "12345";

        set.add(str);

        set.add(str);


        System.out.println("set.size() = " + set.size());

    }

}