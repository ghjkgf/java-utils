package com.common.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FileUtilTest {

    public static void main(String[] args) throws IOException {
        FileUtil.consumeTimelyFile("D:\\MyDesktop\\test.txt", new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.trim());
            }
        });
    }

    @Test
    public void recordFile() {
        ArrayList<File> list = new ArrayList<>();
        FileUtil.recordFile(new File("D:\\代码库\\分布式聊天框架"), file -> file.getName().contains(".java"), list);
        list.forEach(System.out::println);
        System.out.println(list.size());
    }
}