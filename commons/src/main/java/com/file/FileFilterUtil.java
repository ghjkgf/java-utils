package com.file;


import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 *
 * @date:2019/12/21 14:00
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class FileFilterUtil {

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }

        // 1. 文件名
        final String f = args[0];

        // 2. 分隔符
        String s = "";
        if (args.length > 1) {
            s = args[1];
        }

        // 3.是否展示
        boolean flag = false;
        if (args.length > 2) {
            flag = Boolean.valueOf(args[2]);
        }

        System.out.println("================START==================");
        final LinkedList<File> files = new LinkedList<>();
        long start = System.currentTimeMillis();
        final String finalS = s;
        final Predicate<File> filter = file -> file.getName().contains(finalS);

        final File file = new File(f);
        showFile(file, filter, files);

        System.out.println("符合条件的共 " + files.size() + " 个文件 . ");
        System.out.println("共执行时间 : " + (System.currentTimeMillis() - start) + " ms . ");
        System.out.println();
        if (flag) {
            System.out.println("================FILES==================");
            files.forEach(e -> System.out.println(e.getAbsolutePath()));
        }

        System.out.println("================END==================");
    }


    static void showFile(File dir, Predicate<File> filter, List<File> files) {
        if (dir.isDirectory()) {
            File[] file = dir.listFiles();
            for (File f : file) {
                showFile(f, filter, files);
            }
        } else {
            if (filter.test(dir)) {
                files.add(dir);
            }
        }
    }
}
