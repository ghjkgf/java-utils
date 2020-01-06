package com.file;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

/**
 * @date:2019/12/30 19:31
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class ImageUtil {

    public static void scaleImage(String source, String destination, double scale) throws IOException {
        if (scale < 0 || scale > 1) {
            throw new IOException("压缩率 0-1 之间");
        }
        Thumbnails.of(source)
                .scale(0.4)
                .toFile(destination);
    }

    public static void main(String[] args) throws IOException {

        System.out.println(4 ^ 10);

//        scaleImage("C:\\Users\\12986\\Desktop\\img_0670.png", "C:\\Users\\12986\\Desktop\\QQ图片201912312053171.png", 0.5);
    }
}
