package com.common.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class ExcelUtilTest {
    @Test
    public void writeExcel() {
    }

    @Test
    public void writeExcel1() {
    }

    @Test
    public void read1() {
        List<ExcelMode> read = ExcelUtil.read(path,"s1", ExcelMode.class);
        read.forEach(new Consumer<ExcelMode>() {
            @Override
            public void accept(ExcelMode excelMode) {
                System.out.println(excelMode);
            }
        });
    }

    @Test
    public void read2() {
    }

    @Data
    public static class ExcelMode {
        @ExcelProperty(value = "用户名1")
        public String name1;
        @ExcelProperty(value = "用户名2")
        public String name2;
        @ExcelProperty(value = "用户名3")
        public String name3;
        @ExcelProperty(value = "用户名4")
        public String name4;
        @ExcelProperty(value = "用户名5")
        public String name5;
        @ExcelProperty(value = "用户名6")
        public String name6;
        @ExcelProperty(value = "用户名7")
        public String name7;
        @ExcelProperty(value = "用户名8")
        public String name8;
        @ExcelProperty(value = "用户名9")
        public String name9;
        @ExcelProperty(value = "用户名10")
        public String name10;
    }

    private static final String path = "/Users/dong/Desktop/test-1.xls";

    @Test
    public void read() {
        List<ExcelMode> read = ExcelUtil.read(path, ExcelMode.class);
        read.forEach(new Consumer<ExcelMode>() {
            @Override
            public void accept(ExcelMode excelMode) {
                System.out.println(excelMode);
            }
        });
    }

    @Test
    public void write() {
        List<ExcelMode> excelModes = new ArrayList<>();
        for (int x = 0; x < 65535; x++) {
            ExcelMode excelMode = new ExcelMode();
            excelMode.name1 = "name1" + x;
            excelMode.name2 = "name2" + x;
            excelMode.name3 = "name3" + x;
            excelMode.name4 = "name4" + x;
            excelMode.name5 = "name5" + x;
            excelMode.name6 = "name6" + x;
            excelMode.name7 = "name7" + x;
            excelMode.name8 = "name8" + x;
            excelMode.name9 = "name8" + x;
            excelMode.name10 = "name8" + x;
            excelModes.add(excelMode);
        }
        long l = System.currentTimeMillis();
        ExcelUtil.writeExcel(path, "s1", ExcelMode.class, excelModes);
        System.out.println(System.currentTimeMillis() - l);
    }
}