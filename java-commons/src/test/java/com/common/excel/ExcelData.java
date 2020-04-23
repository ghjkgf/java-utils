package com.common.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * todo
 *
 * @date: 2020-04-23 14:40
 * @author：<a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
@Data
public class ExcelData {
    @ExcelProperty("用户ID")
    private String userId;

    @ExcelProperty("违约时间")
    private String date;

    @ExcelProperty("答题时间")
    private String answerDate;

    @ExcelProperty("题目ID")
    private String questionID;

    @ExcelProperty("违约类型")
    private String type;

    @ExcelProperty("正确率")
    private String rate;

}
