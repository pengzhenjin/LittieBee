package com.pzj.common.util;

import androidx.annotation.NonNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @ClassName: TimeUtils
 * @Description: 时间工具类
 * @Author: PengZhenjin
 * @CreateDate: 2019/9/26 21:43
 */
public class TimeUtils {

    private static final ThreadLocal<SimpleDateFormat> SDF_THREAD_LOCAL = new ThreadLocal<>();

    private TimeUtils() {
        throw new UnsupportedOperationException("You can't instantiate me...");
    }

    private static SimpleDateFormat getDefaultFormat() {
        return getDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private static SimpleDateFormat getDateFormat(String pattern) {
        SimpleDateFormat simpleDateFormat = SDF_THREAD_LOCAL.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
            SDF_THREAD_LOCAL.set(simpleDateFormat);
        } else {
            simpleDateFormat.applyPattern(pattern);
        }
        return simpleDateFormat;
    }

    /**
     * 将毫秒转化为字符串，默认时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param millis
     * @return
     */
    public static String millis2String(final long millis) {
        return millis2String(millis, getDefaultFormat());
    }

    /**
     * 将毫秒转化为字符串
     *
     * @param millis
     * @param pattern
     * @return
     */
    public static String millis2String(long millis, @NonNull final String pattern) {
        return millis2String(millis, getDateFormat(pattern));
    }

    /**
     * 将毫秒转化为字符串
     *
     * @param millis
     * @param format
     * @return
     */
    public static String millis2String(final long millis, @NonNull final DateFormat format) {
        return format.format(new Date(millis));
    }

    /**
     * 将时间字符串转换为毫秒，默认时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static long string2Millis(final String time) {
        return string2Millis(time, getDefaultFormat());
    }

    /**
     * 将时间字符串转换为毫秒
     *
     * @param time
     * @param pattern
     * @return
     */
    public static long string2Millis(final String time, @NonNull final String pattern) {
        return string2Millis(time, getDateFormat(pattern));
    }

    /**
     * 将时间字符串转换为毫秒
     *
     * @param time
     * @param format
     * @return
     */
    public static long string2Millis(final String time, @NonNull final DateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转换为date，默认时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static Date string2Date(final String time) {
        return string2Date(time, getDefaultFormat());
    }

    /**
     * 将时间字符串转换为date
     *
     * @param time
     * @param pattern
     * @return
     */
    public static Date string2Date(final String time, @NonNull final String pattern) {
        return string2Date(time, getDateFormat(pattern));
    }

    /**
     * 将时间字符串转换为date
     *
     * @param time
     * @param format
     * @return
     */
    public static Date string2Date(final String time, @NonNull final DateFormat format) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将时间date转换为字符串，默认时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String date2String(final Date date) {
        return date2String(date, getDefaultFormat());
    }

    /**
     * 将时间date转换为字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String date2String(final Date date, @NonNull final String pattern) {
        return getDateFormat(pattern).format(date);
    }

    /**
     * 将时间date转换为字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2String(final Date date, @NonNull final DateFormat format) {
        return format.format(date);
    }

    /**
     * 将时间date转换为毫秒
     *
     * @param date
     * @return
     */
    public static long date2Millis(final Date date) {
        return date.getTime();
    }

    /**
     * 将时间date转换为毫秒
     *
     * @param millis
     * @return
     */
    public static Date millis2Date(final long millis) {
        return new Date(millis);
    }

    /**
     * 获取当前时间并转换为string，默认时间格式 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTime() {
        return millis2String(System.currentTimeMillis(), getDefaultFormat());
    }

    /**
     * 获取当前时间并转换为string
     *
     * @param pattern
     * @return
     */
    public static String getCurrentTime(@NonNull final String pattern) {
        return millis2String(System.currentTimeMillis(), getDateFormat(pattern));
    }

    /**
     * 获取当前时间并转换为string
     *
     * @param format
     * @return
     */
    public static String getCurrentTime(@NonNull final DateFormat format) {
        return millis2String(System.currentTimeMillis(), format);
    }

    /**
     * 判断是否是今天
     *
     * @param time
     * @param format
     * @return
     */
    public static boolean isToday(final String time, @NonNull final DateFormat format) {
        return isToday(string2Millis(time, format));
    }

    /**
     * 判断是否是今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(final Date date) {
        return isToday(date.getTime());
    }

    /**
     * 判断是否是今天
     *
     * @param millis
     * @return
     */
    public static boolean isToday(final long millis) {
        long wee = getWeeOfToday();
        return millis >= wee && millis < wee + 86400000;
    }

    /**
     * 判断是否是闰年
     *
     * @param time
     * @param format
     * @return
     */
    public static boolean isLeapYear(final String time, @NonNull final DateFormat format) {
        return isLeapYear(string2Date(time, format));
    }

    /**
     * 判断是否是闰年
     *
     * @param millis
     * @return
     */
    public static boolean isLeapYear(final long millis) {
        return isLeapYear(millis2Date(millis));
    }

    /**
     * 判断是否是闰年
     *
     * @param date
     * @return
     */
    public static boolean isLeapYear(final Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    /**
     * 判断是否是闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(final int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 获取当前时间为本月的第几周
     *
     * @return WeekOfMonth
     */
    public static int getWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week - 1;
    }

    /**
     * 获取当前时间为本周的第几天
     *
     * @return DayOfWeek
     */
    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {
            day = 7;
        } else {
            day = day - 1;
        }
        return day;
    }

    /**
     * 获取当前时间的年份
     *
     * @return
     */
    public static int getYear() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取当前时间的月份
     *
     * @return
     */
    public static int getMonth() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取当前时间是哪天
     *
     * @return
     */
    public static int getDay() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取友好的时间，时间格式：yyyy-MM-dd HH:mm:ss
     * 如果小于 1 秒钟内，显示刚刚
     * 如果在 1 分钟内，显示XXX秒前
     * 如果在 1 小时内，显示XXX分钟前
     * 如果在 1 小时外的今天内，显示今天15:32
     * 如果是昨天的，显示昨天15:32
     * 其余显示，2016-10-15
     * 时间不合法的情况，显示全部日期和时间信息，如：星期六 十月 27 14:21:20 CST 2007
     *
     * @param time
     * @return
     */
    public static String getFriendlyTime(final String time) {
        return getFriendlyTime(time, getDefaultFormat());
    }

    /**
     * 获取友好的时间
     * 如果小于 1 秒钟内，显示刚刚
     * 如果在 1 分钟内，显示XXX秒前
     * 如果在 1 小时内，显示XXX分钟前
     * 如果在 1 小时外的今天内，显示今天15:32
     * 如果是昨天的，显示昨天15:32
     * 其余显示，2016-10-15
     * 时间不合法的情况，显示全部日期和时间信息，如：星期六 十月 27 14:21:20 CST 2007
     *
     * @param time
     * @param format
     * @return
     */
    public static String getFriendlyTime(final String time, @NonNull final DateFormat format) {
        return getFriendlyTime(string2Millis(time, format));
    }

    /**
     * 获取友好的时间
     * 如果小于 1 秒钟内，显示刚刚
     * 如果在 1 分钟内，显示XXX秒前
     * 如果在 1 小时内，显示XXX分钟前
     * 如果在 1 小时外的今天内，显示今天15:32
     * 如果是昨天的，显示昨天15:32
     * 其余显示，2016-10-15
     * 时间不合法的情况，显示全部日期和时间信息，如：星期六 十月 27 14:21:20 CST 2007
     *
     * @param date
     * @return
     */
    public static String getFriendlyTime(final Date date) {
        return getFriendlyTime(date.getTime());
    }

    /**
     * 获取友好的时间
     * 如果小于 1 秒钟内，显示刚刚
     * 如果在 1 分钟内，显示XXX秒前
     * 如果在 1 小时内，显示XXX分钟前
     * 如果在 1 小时外的今天内，显示今天15:32
     * 如果是昨天的，显示昨天15:32
     * 其余显示，2016-10-15
     * 时间不合法的情况，显示全部日期和时间信息，如：星期六 十月 27 14:21:20 CST 2007
     *
     * @param millis
     * @return
     */
    public static String getFriendlyTime(final long millis) {
        long now = System.currentTimeMillis();
        long span = now - millis;
        if (span < 0) {
            return String.format("%tc", millis);
        }
        if (span < 1000) {
            return "刚刚";
        } else if (span < 60000) {
            return String.format(Locale.getDefault(), "%d秒前", span / 1000);
        } else if (span < 3600000) {
            return String.format(Locale.getDefault(), "%d分钟前", span / 60000);
        }
        // 获取当天 00:00
        long wee = getWeeOfToday();
        if (millis >= wee) {
            return String.format("今天%tR", millis);
        } else if (millis >= wee - 86400000) {
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
