package com.file;

import com.google.common.io.*;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * GuavaFile 文件IO 学习
 *
 * @date:2019/12/8 15:25
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */

public class GuavaFile {

    public static void main(String[] args) throws IOException {
   /*     ByteSource byteSource = Files.asByteSource(new File("C:\\Users\\12986\\Desktop\\2019年学生测试数据.xls"));
        byteSource.copyTo(new GZIPOutputStream(new FileOutputStream("C:\\Users\\12986\\Desktop\\2019年学生测试数据1.zip")));


        ByteSink sink = Files.asByteSink(new File("C:\\Users\\12986\\Desktop\\2019年学生测试数据.xls"));
*/

        CharSink charSink = Files.asCharSink(new File("D:\\代码库\\javase学习\\DevelopmentJAVA\\JavaSeBasics\\file\\out4.txt"), Charset.forName("utf-8"), FileWriteMode.APPEND);


        Closer closer = Closer.create();
    }


    @Test
    public void testStart() throws IOException {
        ByteSource source = Files.asByteSource(new File("file\\in.txt"));
        source.copyTo(Files.asByteSink(new File("file\\out.txt")));
    }


    @Test
    public void testSource() throws IOException {

        ByteSource source = Files.asByteSource(new File("file\\in.txt"));

        source.sizeIfKnown();// 防止空指针

        source.copyTo(new FileOutputStream("....")); // 拷贝


        source.copyTo(Files.asByteSink(new File(""), FileWriteMode.APPEND)); // 拷贝


        source.slice(0, 10); // 切割


        source.read();  // 读取一个流


        source.asCharSource(Charset.forName("utf-8")); // 读取一个CharSource流(字符流)


//        source.hash() // 求hash码


        source.openStream(); // 返回一个 InputStream 对象


    }


    @Test
    public void testSink() throws IOException {


//        charSink.write(new StringBuffer("hello world"));

/*
        ByteSink byteSink = Files.asByteSink(new File(""));

        byteSink.openStream();  // 返回一个 OutputStream 对象

        byteSink.write(new byte[1024]);  // 往文件中写入字节数组

        byteSink.writeFrom(new FileInputStream(""));  // 写入一个文件输入流*/

//        byteSink.asCharSink();  // 转换成 CharSink

    }


    @Test
    public void test() throws IOException {

        File dest = new File("C:\\Users\\12986\\Desktop\\pom2.txt");
//        dest.deleteOnExit();

        ByteSink byteSink = Files.asByteSink(dest);

        File file = new File("C:\\Users\\12986\\Desktop\\pom1.txt");
        byteSink.write(Files.toByteArray(file));

    }


    @Test
    public void testfile() throws IOException {

        ByteSource source = Files.asByteSource(new File("D:\\樊浩东\\media\\新建文件夹 (2)\\alex-radelich-AUbVjzmzhwI-unsplash.jpg"));


        byte[] read = source.read();


        int length = read.length;
        int delimiter = 1024;


        if (length >= delimiter) {

        } else {

        }


        System.out.println(read.length);

    }


    @Test
    public void test3() throws Exception {
        RandomAccessFile file = new RandomAccessFile(new File("D:\\樊浩东\\media\\新建文件夹 (2)\\alex-radelich-AUbVjzmzhwI-unsplash.jpg"), "r");

        // 总长度
        long size = file.length();
        System.out.println(size);

        // 每段多少
        int delimiter = 1024;

        // 需要拆多少个包 , 防止拷贝
        int block = (int) (size % delimiter == 0 ? size / delimiter : (size / delimiter) + 1);

        // 新建数组
        ArrayList<byte[]> list = new ArrayList<>(block);

        // 打开文件流
        FileChannel channel = file.getChannel();

        // 1. 起始位置
        long position = channel.position();

        // 2. 只有大于他才执行
        while (size > delimiter) {

            ByteBuffer buffer = ByteBuffer.allocate(delimiter);
            channel.read(buffer, position);

            byte[] array = buffer.array();
            list.add(array);

            size = size - delimiter;
            position = position + delimiter;
        }

        // 最后一次绝对不满
        ByteBuffer buffer = ByteBuffer.allocate(delimiter);
        channel.read(buffer, position);
        byte[] bytes = new byte[(int) size];
        buffer.flip();

        buffer.get(bytes);
        list.add(bytes);
    }

    static List<byte[]> cuttingFile(File fileName, int delimiter) throws Exception {

        // try - with - resource
        try (RandomAccessFile file = new RandomAccessFile(fileName, "r"); FileChannel channel = file.getChannel()) {
            // 总长度
            long size = file.length();

            // 需要拆多少个包 , 防止拷贝
            int block = (int) (size % delimiter == 0 ? size / delimiter : (size / delimiter) + 1);

            // 新建数组
            ArrayList<byte[]> list = new ArrayList<>(block);


            // 1. 起始位置
            long position = channel.position();

            // 2. 只有大于他才执行
            while (size > delimiter) {

                ByteBuffer buffer = ByteBuffer.allocate(delimiter);
                channel.read(buffer, position);

                byte[] array = buffer.array();
                list.add(array);

                size = size - delimiter;
                position = position + delimiter;
            }

            // 最后一次绝对不满
            ByteBuffer buffer = ByteBuffer.allocate(delimiter);
            channel.read(buffer, position);

            byte[] bytes = new byte[(int) size];
            buffer.flip();

            buffer.get(bytes);
            list.add(bytes);

            return list;
        } catch (Exception e) {
            throw new RuntimeException("文件拆分异常");
        }
    }





}

