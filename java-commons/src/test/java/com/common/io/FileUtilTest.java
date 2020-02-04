package com.common.io;

import java.io.IOException;
import java.util.function.Consumer;

public class FileUtilTest {

    public static void main(String[] args) throws IOException {
        FileUtil.consumeTimelyFile("D:\\MyDesktop\\test.txt", new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.trim());
            }
        });
    }
}