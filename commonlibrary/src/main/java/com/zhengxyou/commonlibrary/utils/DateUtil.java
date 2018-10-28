package com.zhengxyou.commonlibrary.utils;

import android.annotation.SuppressLint;

import com.zhengxyou.commonlibrary.constant.TimeConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final class Pattern {
        public static final String YYYYMMDD_ = "yyyy-MM-dd";
        public static final String YYYY_MM_DD = "yyyy-MM-dd";
        public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
        public static final String YYYY_MM = "yyyy-MM";
        public static final String XIEGANG_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";
        public static final String XIEGANG_YYYY_MM_DD = "yyyy/MM/dd";
        public static final String ZHCN_YYYY_MM_DD = "yyyy年MM月dd日";
        public static final String ZHCN_YYYY_MM_DD_HH_MM_SS = "yyyy年MM月dd日HH时mm分ss秒";
        public static final String ZHCN_YYYY_MM_DD_HH_MM = "yyyy年MM月dd日HH时mm分";
        public static final String ZHCN_MM_DD = "MM月dd日";
    }

    public static SimpleDateFormat getDefaultFormat() {
        return new SimpleDateFormat(Pattern.YYYY_MM_DD_HH_MM_SS, Locale.getDefault());
    }

    /*
     * 将时间戳转换为时间
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatDate(long time, String format) {
        return new SimpleDateFormat(format).format(new Date(time));
    }

    /**
     * 将时间戳字符串转换为时间
     *
     * @param time   时间字符串
     * @param format 格式
     * @return 日期
     */
    @SuppressLint("SimpleDateFormat")
    public static String formatDate(String time, String format) {
        return formatDate(Long.parseLong(time), format);
    }

    public static String date2Str(Date date) {
        return date2Str(date, Pattern.YYYY_MM_DD_HH_MM_SS);
    }

    @SuppressLint("SimpleDateFormat")
    public static String date2Str(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将时间戳字符串转换为时间
     *
     * @param time 时间戳
     * @return 日期
     */
    public static Date str2Date(String time) {
        return new Date(Long.parseLong(time));
    }

    /**
     * 时间字符串转为时间戳
     *
     * @param time 时间字符串.
     * @return 时间戳
     */
    public static long str2Millis(String time) {
        return str2Millis(time, Pattern.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间字符串转为时间戳
     *
     * @param time   时间字符串
     * @param format 转换格式.
     * @return the 时间戳
     */
    @SuppressLint("SimpleDateFormat")
    public static long str2Millis(String time, String format) {
        try {
            return new SimpleDateFormat(format).parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 和当前日期比较大小
     *
     * @param dateString 要比较的日期字符串
     * @param format     要比较到那个级别
     * @return 比当前大放回false，小于等于当前放回true
     */
    public static boolean compareBeforeNow(String dateString, String format) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            Date now = new Date();
            Date compareDate = df.parse(dateString);
            return compareDate.before(now);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 与今天天相差的天数
     *
     * @param date 要比较的日期
     * @return 天数
     */
    public static int DaysOfBetween(Date date) {
        return DaysOfBetween(Calendar.getInstance().getTime(), date);
    }

    /**
     * 2个日期的相隔天数
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 天数
     */
    public static int DaysOfBetween(Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(end);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        return Long.valueOf((calendar.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000 * 60 * 60 * 24)).intValue();
    }

    /**
     * 返回友好时间跨度提示
     *
     * @param millis 时间戳
     * @return 时间跨度提示
     * <ul>
     * <li>如果小于 1 秒钟内，显示刚刚</li>
     * <li>如果在 1 分钟内，显示 XXX秒前</li>
     * <li>如果在 1 小时内，显示 XXX分钟前</li>
     * <li>如果在 1 小时外的今天内，显示今天15:32</li>
     * <li>如果是昨天的，显示昨天15:32</li>
     * <li>其余显示，2016-10-15</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     * <P>%tx 日期与时间类型（x代表不同的日期与时间转换符</P>
     * <ul>
     * <li>tc:全部日期和时间信息</li>
     * <li>tF:年-月-日格式</li>
     * <li>tr:HH:MM:SS PM格式（12时制）</li>
     * <li>tT:HH:MM:SS格式（24时制）</li>
     * <li>tR:HH:MM格式（24时制</li>
     * </ul>
     */
    public static String getFriendlyTimeSpanByNow(long millis) {
        long now = System.currentTimeMillis();
        long span = now - millis;
        if (span < 0)
            return String.format("%tc", millis);
        if (span < 1000) {
            return "刚刚";
        } else if (span < TimeConstants.MIN) {
            return String.format(Locale.getDefault(), "%d秒前", span / TimeConstants.SEC);
        } else if (span < TimeConstants.HOUR) {
            return String.format(Locale.getDefault(), "%d分钟前", span / TimeConstants.MIN);
        }
        // 获取当天 00:00
        long wee = getWeeOfToday();
        if (millis >= wee) {
            return String.format("今天%tR", millis);
        } else if (millis >= wee - TimeConstants.DAY) {
            return String.format("昨天%tR", millis);
        } else {
            return String.format("%tF", millis);
        }
    }

    private static long getWeeOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

}
