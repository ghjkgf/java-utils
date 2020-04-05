package com.common.io;

import com.common.thread.ExecutorUtil;
import org.junit.Test;
import org.xerial.snappy.Snappy;
import org.xerial.snappy.SnappyNative;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FileUtilTest {


    @Test
    public void recordFile() {
        ArrayList<File> list = new ArrayList<>();
        FileUtil.recordFile(new File("D:\\代码库\\分布式聊天框架"), file -> file.getName().contains(".java"), list);
        list.forEach(System.out::println);
        System.out.println(list.size());
    }

    //    @Test
    public static void gzip() throws IOException {
        FileInputStream inputStream = new FileInputStream("D:\\代码库\\分布式聊天框架\\chat-framework\\server.log");
        int available = inputStream.available();
        byte[] bytes = new byte[available];
        int read = inputStream.read(bytes);
        System.out.println(read);
        byte[] gzip = FileUtil.gzip(bytes);
//        System.out.println(gzip.length);
//        byte[] unGzip = FileUtil.unGzip(gzip);
//        System.out.println(unGzip.length);
        inputStream.close();
        FileOutputStream stream = new FileOutputStream("D:\\代码库\\分布式聊天框架\\chat-framework\\server1.log");
        stream.write(gzip);
        stream.close();
    }

    @Test
    public void testSnappy() throws IOException {
//        FileInputStream inputStream = new FileInputStream("D:\\代码库\\分布式聊天框架\\chat-framework\\server.log");
//        int available = inputStream.available();
//        byte[] bytes = new byte[available];
//        int read = inputStream.read(bytes);
//        long start = System.currentTimeMillis();
//
//        for (int x = 1; x <= 10; x++) {
//            FileUtil.compressFile(new File("D:\\代码库\\分布式聊天框架\\chat-framework\\chat-lient.log"), new File("D:\\代码库\\分布式聊天框架\\chat-framework\\chat-lient.zip"), FileUtil.GZIP_COMPRESS);
//            FileUtil.compressFile(new File("D:\\代码库\\分布式聊天框架\\chat-framework\\chat-lient.zip"), new File("D:\\代码库\\分布式聊天框架\\chat-framework\\chat-lient" + x + ".log"), FileUtil.GZIP_UNCOMPRESS);
//        }
//        Snappy.compress()

//
        FileInputStream stream = new FileInputStream(new File("D:\\代码库\\分布式聊天框架\\chat-framework\\chat-lient.log"));

        FileChannel channel = stream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(stream.available());
        channel.read(buffer);
        buffer.flip();
        ByteBuffer direct = ByteBuffer.allocateDirect(stream.available() / 8);
        Snappy.compress(buffer, direct);

        FileOutputStream outputStream = new FileOutputStream(new File("D:\\代码库\\分布式聊天框架\\chat-framework\\chat-lient.snappy"));
        FileChannel channel1 = outputStream.getChannel();
        direct.flip();
        channel1.write(direct);
//        System.out.println(buffer);
        channel1.close();
        outputStream.close();

        channel.close();
        stream.close();


//        channel.write();


//        channel.write();


//        System.out.println(System.currentTimeMillis() - start);
    }


    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}