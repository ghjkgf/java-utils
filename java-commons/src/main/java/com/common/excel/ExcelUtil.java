package com.common.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.event.SyncReadListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

/**
 * excel的 读写工具包
 *
 * @author fanhaodong516@gmail.com
 * @since 2020-08-22
 */
public class ExcelUtil {

    /**
     * write
     *
     * @param filePath  文件路径
     * @param sheetName sheet名字
     * @param rowClass  写入的时候，每行的数据类型
     * @param rowDates  写入的数据，列表
     * @param <T>       类型
     */
    public static <T> void writeExcel(String filePath, String sheetName, Class<T> rowClass, List<T> rowDates) {
        EasyExcel.write(filePath, rowClass).sheet(sheetName).doWrite(rowDates);
    }

    public static <T> void writeExcel(OutputStream stream, String sheetName, Class<T> rowClass, List<T> rowDates) {
        ExcelWriterBuilder excelWriterBuilder = new ExcelWriterBuilder();
        excelWriterBuilder.file(stream);
        if (rowClass != null) {
            excelWriterBuilder.head(rowClass);
        }
        excelWriterBuilder.sheet(sheetName).doWrite(rowDates);
    }


    /**
     * read
     *
     * @param filePath 读取的文件
     * @param rowClass 读取的每行的结构
     */
    @SuppressWarnings("all")
    public static <T> List<T> read(String filePath, Class<T> rowClass) {
        SyncReadListener syncReadListener = new SyncReadListener();
        EasyExcelFactory.read(new File(filePath), rowClass, syncReadListener).doReadAll();
        return (List<T>) syncReadListener.getList();
    }


    /**
     * @param filePath 读取的文件
     * @param sheet    sheet名字
     * @param rowClass 读取的每行的结构
     */
    @SuppressWarnings("all")
    public static <T> List<T> read(String filePath, String sheet, Class<T> rowClass) {
        SyncReadListener syncReadListener = new SyncReadListener();
        EasyExcelFactory.read(filePath).sheet(sheet).head(rowClass).registerReadListener(syncReadListener).doRead();
        return (List<T>) syncReadListener.getList();
    }
}
