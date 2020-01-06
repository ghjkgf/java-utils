package com.file;

import org.xerial.snappy.Snappy;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * TODO
 *
 * @date:2019/12/30 19:33
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class CompressFileUtil {


    static final Charset UTF_8 = Charset.forName("UTF-8");


    public static void main(String[] args) throws IOException {
        test();
    }

    static void test() throws IOException {

        String msg = "A B C D E F G";

        byte[] bytes = Snappy.compress(msg, UTF_8);


        byte[] bytes1 = Snappy.uncompress(bytes);
        System.out.println(new String(bytes1, UTF_8));

    }


}
