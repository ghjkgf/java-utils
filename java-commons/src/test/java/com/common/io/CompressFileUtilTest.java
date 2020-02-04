package com.common.io;

import java.io.File;

public class CompressFileUtilTest {

    public static void main(String[] args) throws Exception {
        FileUtil.compressFile(new File("D:\\MyDesktop\\test1.txt"),
                new File("D:\\MyDesktop\\test2.txt"),
                FileUtil.GZIP_UNCOMPRESS);
    }
}