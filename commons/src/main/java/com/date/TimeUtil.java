package com.date;

/**
 * 时间工具类
 *
 * @author kingston
 */
public class TimeUtil {


    /**
     * 一毫秒多少纳秒  10^6
     */
    public static final long NANOSECONDS_OF_MILLSECOND = 100000L;


    /**
     * 1S 多少 纳秒  10^9
     */
    public static final long NANOSECONDS_OF_SECOND = 100000000L;




    /**
     * 一毫秒
     */
    public static final long MILLISECOND = 1L;


    /**
     * 一秒的毫秒数
     */
    public static final long ONE_SECOND = MILLISECOND * 1000;
    /**
     * 一分的毫秒数
     */
    public static final long ONE_MINUTE = ONE_SECOND * 60;
    /**
     * 一时的毫秒数
     */
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    /**
     * 一天的毫秒数
     */
    public static final long ONE_DAY = ONE_HOUR * 24;
    /**
     * 一天总共有{@value}小时
     */
    public static final int HOURS_OF_DAY = 24;

}
