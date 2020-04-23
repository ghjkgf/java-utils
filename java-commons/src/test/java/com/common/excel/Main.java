package com.common.excel;

import com.alibaba.excel.EasyExcel;
import com.common.io.IOUtils;
import com.common.string.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * todo
 *
 * @date: 2020-04-23 14:44
 * @author：<a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class Main {
    private static final int NUM_5=5;

    private static final int NUM_2=2;
    private static final ArrayList<String> EMPTY_LIST=new ArrayList<>();

    public static void main(String[] args) throws IOException {

        File file = new File("/Users/sgcx015/Desktop/2020-3-19到4-08号使用答题功能后再次被罚的用户答题情以及违规情况.txt");
        String[] strings = IOUtils.readLines(file);
//

        ArrayList<ExcelData> data = new ArrayList<>(90000);

        for (String string : strings) {
            ArrayList<String> handler = handler(string);
            if (handler.size()==3){
                ExcelData excelData = new ExcelData();
                excelData.setUserId(handler.get(0));
                excelData.setDate(handler.get(1));
                excelData.setType(handler.get(2));
                data.add(excelData);
                continue;
            }
            if (handler.size()==6){
                ExcelData excelData = new ExcelData();
                excelData.setUserId(handler.get(0));
                excelData.setDate(handler.get(1));
                excelData.setAnswerDate(handler.get(2));
                excelData.setQuestionID(handler.get(3));
                excelData.setType(handler.get(4));
                excelData.setRate(handler.get(5));
                data.add(excelData);
            }
        }


        EasyExcel.write("/Users/sgcx015/Desktop/test.xlsx", ExcelData.class).sheet("data-1").doWrite(data);

    }


    public static ArrayList<String> handler(String str){
        ArrayList<String> list = new ArrayList<>(5);
        String[] split = StringUtils.split(str, "\t");
        String num_data = split[0];

        String[] num_data_spl = StringUtils.split(num_data, "  ");
        if (num_data_spl.length==3){
            list.add(num_data_spl[0]);
            list.add(num_data_spl[1]+" "+num_data_spl[2]);
        }


        if (split.length==2){
            list.add(split[1]);
            return list;
        }

        if (split.length==5){
            list.add(split[1]);
            list.add(split[2]);
            list.add(split[3]);
            list.add(split[4]);
            return list;
        }
        return EMPTY_LIST;
    }
}
