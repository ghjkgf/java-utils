package com.common.time;


/**
 * 时间工具类 , 以纳秒为最低的计算单位
 *
 * @author <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public class NanosecondUtil {

    /**
     * 纳秒 , 最小单位
     */
    public static final long Nanosecond = 1;

    public static final long Microsecond = 1000 * Nanosecond;

    public static final long Millisecond = 1000 * Microsecond;

    public static final long Second = 1000 * Millisecond;

    public static final long Minute = 60 * Second;

    public static final long Hour = 60 * Minute;

    public static final long DAY = 24 * Hour;

    public static final long minDuration = -(Nanosecond << 63);

    public static final long maxDuration = (Nanosecond << 63) - 1;


}
