package com.common.io;

import com.common.string.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @date:2020/4/1 21:20
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class MDFile {

    private static final Logger logger = Logger.getInstance("D:\\MyDesktop\\out\\log\\out.log");

    /**
     * 自定义日志
     */
    static class Logger {

        private final PrintStream writer;

        private Logger(PrintStream writer) {
            this.writer = writer;
        }

        public static Logger getInstance(String logFile) {
            try {
                return new Logger(new PrintStream(new File(logFile)));
            } catch (FileNotFoundException e) {
                System.err.println(String.format("日志初始化异常 : %s", e));
                return new Logger(System.out);
            }
        }

        public static Logger getInstance() {
            return new Logger(System.out);
        }


        public void info(String info) {
            writer.println(String.format("[INFO] - [%s] - [%s] - %s"
                    , Thread.currentThread().getName()
                    , new Date()
                    , info
            ));
        }

        public void error(String error) {
            writer.println(String.format("[ERROR] - [%s] - [%s] - %s"
                    , Thread.currentThread().getName()
                    , new Date()
                    , error
            ));
        }

    }


    static class MD {

        // 这些字符都是不可以当做文件夹名称的
        private static final String[] str = {"/", "\\", ":", "*", "\"", "<", ">", "|", "?"};

        // 写了个正则 . 替换.
        private static final String _PATTERN = "[\\\\\"*|/><@#:?)(]";


        private static final String _START = "---";

        private static final String _TITLE = "title:";

        private static final String _PIC = "feature:";

        private static final String _TAGS = "tags:";


        private static final String _Date = "date:";

        private static final String _FILE_FORMAT = ".md";

        /**
         * 获取系统的分隔符
         */
        private static final String _FILE_SEPARATOR = System.getProperty("file.separator");

        /**
         * 是否需要写出图片
         */
        private boolean needWritePicture = false;


        /**
         * 是否需要写入标题
         */
        private boolean needWriteTitle = false;

        /**
         * 设置输出需要写图片
         */
        public void setNeedWritePicture(boolean needWritePicture) {
            this.needWritePicture = needWritePicture;
        }

        /**
         * 设置输出需要写标题
         */
        public void setNeedWriteTitle(boolean needWriteTitle) {
            this.needWriteTitle = needWriteTitle;
        }

        /**
         * 全局行
         */
        private String[] lines;

        /**
         * 是否需要些
         */
        private boolean[] isWrite;


        /**
         * 文件
         */
        private File file;


        /**
         * 构造器 . 看需求
         */
        public MD(String file) {
            this(new File(file));
        }

        public MD(File file) {
            this.file = file;
        }

        private String pic;

        private String title;

        private String[] tags;


        private boolean hasPic = false;

        private boolean hasTags = false;

        private boolean hasTitle = false;

        private boolean init = false;

        private boolean hasInit = false;


        /**
         * 读取文件中的数据
         */
        private void initLine() throws IOException {
            if (this.file != null) {
                this.lines = IOUtils.readLines(file);
                this.isWrite = new boolean[lines.length];
            } else {
                throw new RuntimeException("文件为空,初始化失败");
            }
        }


        /**
         * 初始化信息 , 包含title信息
         */
        public void init() throws IOException {

            // 如果file 不为空, 说明走的是file 初始化.
            initLine();

            // 再次判断
            if (lines == null || isWrite == null) {
                throw new RuntimeException("初始化失败");
            }

            // 记录一些初始化信息
            for (int i = 0; i < lines.length; i++) {
                // 没有拿到 pic 和 title
                if (!init) {
                    // 直接不写.
                    isWrite[i] = true;

                    // 读取
                    String line = lines[i];

                    // 如果开始.
                    if (line.equals(_START) && !hasTitle && !hasPic) {
                        continue;
                    }
                    if (line.startsWith(_TITLE)) {
                        this.title = handlerTitle(line);
                        hasTitle = true;
                        continue;
                    }

                    if (line.startsWith(_PIC)) {
                        this.pic = handlerPic(line);
                        hasPic = true;
                        continue;
                    }

                    if (line.startsWith(_TAGS)) {
                        hasTags = true;
                        this.tags = handlerTag(line);
                        continue;
                    }

                    if (hasPic && hasTitle) {
                        if (line.equals(_START)) {
                            init = true;
                        }
                    }
                } else {
                    hasInit = true;
                    break;
                }
            }
        }


        /**
         * 写出文件
         */
        public void write(String filePath) throws IOException {
            // 如果没有初始化. 防止用户调用 init 然后再调用 write.
            if (!hasInit) {
                init();
            }

            String file;
            if (filePath.endsWith(_FILE_SEPARATOR)) {
                file = filePath + this.title + _FILE_FORMAT;
            } else {
                file = filePath + _FILE_SEPARATOR + this.title + _FILE_FORMAT;
            }


            try (BufferedWriter stream = new BufferedWriter(new FileWriter(file))) {
                // 可能需要写某些玩意
                prepare(stream);
                for (int i = 0; i < this.isWrite.length; i++) {
                    if (!isWrite[i]) {
                        stream.write(this.lines[i]);
                        stream.newLine();
                    }
                }
                logger.info(String.format("写出成功 : %s , 文档标题是 : %s , 保存位置 : %s", this.file.getName(), this.title, file));
            } catch (FileNotFoundException e) {
                // 修改 title
                String new_path = handlerPath(title);
                logger.error(String.format("写出失败 : %s , 尝试修改标题 : %s  ->  %s", this.file.getName(), title, new_path));
                fail(filePath, new_path);
            }
        }

        /**
         * 一直替换
         */
        private static String handlerPath(String filePath) {
            String _1 = filePath.replaceAll(_PATTERN, " ");
            String _2 = _1.replace('/', ' ');
            return _2.replace('\\', ' ');
        }


        /**
         * 如果是因为 分隔符的问题. 根据正则去替换
         *
         * @param filePath
         * @throws IOException
         */
        private void fail(String filePath, String new_title) throws IOException {
            String file;
            if (filePath.endsWith(_FILE_SEPARATOR)) {
                file = filePath + new_title + _FILE_FORMAT;
            } else {
                file = filePath + _FILE_SEPARATOR + new_title + _FILE_FORMAT;
            }

            try (BufferedWriter stream = new BufferedWriter(new FileWriter(file))) {
                // 可能需要写某些玩意
                prepare(stream);
                for (int i = 0; i < this.isWrite.length; i++) {
                    if (!isWrite[i]) {
                        stream.write(this.lines[i]);
                        stream.newLine();
                    }
                }
                logger.info(String.format("写出成功 : %s , 文档标题是 : %s , 保存位置 : %s", this.file.getName(), this.title, file));
            }
        }

        //TITLE_PRE+ title
        private static final String TITLE_PRE = "# ";
        // PIC_PRE+url+PIC_SUF
        private static final String PIC_PRE = "![](";
        private static final String PIC_SUF = ")";

        /**
         * 可能需要写入其他东西
         */
        public void prepare(BufferedWriter stream) throws IOException {
            if (needWriteTitle) {
                String title_line = TITLE_PRE + this.title;
                stream.write(title_line);
                stream.newLine();
            }

            if (needWritePicture) {
                String pic_line = PIC_PRE + this.pic + PIC_SUF;
                stream.write(pic_line);
                stream.newLine();
            }
        }

        public String getPic() {
            return pic;
        }

        public String getTitle() {
            return title;
        }

        public String[] getTags() {
            return tags;
        }

        public String[] getLines() {
            return lines;
        }

        public boolean[] getIsWrite() {
            return isWrite;
        }


        /**
         * 处理字符串的
         */
        private static String handlerPic(String pic) {
            int length = MD._PIC.length() + 1;
            return pic.substring(length);
        }


        private static String handlerData(String data) {
            int length = MD._Date.length() + 1;
            return data.substring(length);
        }


        private static String[] handlerTag(String tags) {
            int length = MD._TAGS.length() + 1;
            String s = tags.substring(length);

            String str = s.substring(1, s.length() - 1);

            return StringUtils.split(str, ',', false);
        }

        private static String handlerTitle(String title) {
            String[] split = StringUtils.split(title, '\'', false);
            if (split.length == 2) {
                return split[1];
            }
            return title;
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        String source = "C:\\Users\\12986\\Documents\\Gridea\\posts";

        String des = "D:\\MyDesktop\\out";
        ArrayList<File> list = new ArrayList<>();
        FileUtil.recordFile(new File(source), list);


        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

        list.forEach(file -> pool.execute(() -> {
            try {
                MD md = new MD(file);
                md.setNeedWritePicture(true);
                md.write(des);
            } catch (IOException e) {
                logger.error("未修改成功 : " + e.getMessage());
            }
        }));

        pool.shutdown();
        pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
}
