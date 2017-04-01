package org.mywap.util;


/*
 * 文 件 名:  DateTools.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zkf56839
 * 修改时间:  Mar 16, 2012
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;


/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author zKF56839
 * @version [版本号, Mar 16, 2012]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DateTools
{
    /** Automatically generated javadoc for: ONE_MINUTE */
    private static final int ONE_MINUTE = 60;
    
    /** Automatically generated javadoc for: ONE_THOUSAND */
    private static final int ONE_THOUSAND = 1000;
    
    /** Automatically generated javadoc for: ONE_HOUR */
    private static final int ONE_HOUR = 60;
    
    /** 时间格式 : yyyy/MM/dd HH:mm */
    public static final String PATTERN = "yyyy/MM/dd HH:mm";
    
    /** 时间格式 : yyyyMMddHHmm */
    public static final String DATE_FORMAT_12 = "yyyyMMddHHmm";
    
    /** 时间格式 : yyyyMMddHHmmss */
    public static final String DATE_FORMAT_14 = "yyyyMMddHHmmss";
    
    /** 时间格式 : yyyyMM */
    public static final String DATE_FORMAT_6 = "yyyyMM";
    
    /** 时间格式 : yyyy-MM */
    public static final String DATE_FORMAT_7 = "yyyy-MM";
    
    /** 时间格式 : yyyyMMdd */
    public static final String DATE_FORMAT2_8 = "yyyyMMdd";
    
    /** 时间格式 : yy.MM.dd */
    public static final String DATE_FORMAT_8 = "yy.MM.dd";
    
    /** 时间格式 : [yyyy-MM-dd hh:mm:ss] */
    public static final String DATE_FORMAT_21 = "[yyyy-MM-dd hh:mm:ss]";
    
    /** 时间格式 : [yyyy-MM-dd HH:mm:ss] */
    public static final String DATE_FORMAT_24HOUR_21 = "[yyyy-MM-dd HH:mm:ss]";
    
    /** 时间格式 ：yyyy-MM-dd hh:mm */
    public static final String DATE_FORMAT_16 = "yyyy-MM-dd hh:mm";
    
    /** 时间格式 : yy-MM-dd hh:mm */
    public static final String DATE_PATTERN_14 = "yy-MM-dd hh:mm";
    
    /** 时间格式 : yy-MM-dd hh:mm */
    public static final String DATE_FORMAT_24HOUR_16 = "yyyy-MM-dd HH:mm";
    
    /** 时间格式 : yy-MM-dd HH:mm */
    public static final String DATE_PATTERN_24HOUR_14 = "yy-MM-dd HH:mm";
    
    /** 时间格式 ：yyyy-MM-dd */
    public static final String DATE_FORMAT_10 = "yyyy-MM-dd";
    
    public static final String DATE_FORMAT_18 = "yyyy年MM月";
    
    public static final String DATE_FORMAT_YEAR = "yyyy";
    
    public static final String DATE_FORMAT_MONTH="MM";
    
    public static final String DATE_FORMAT_DAY="dd";
    
    /** 时间格式 : yyyy-MM-dd HH:mm:ss */
    public static final String DATE_FORMAT_24HOUR_19 = "yyyy-MM-dd HH:mm:ss";
    
    /** 时间格式 : MM-dd hh:mm */
    public static final String DATE_PATTERN_8 = "MM-dd hh:mm";
    
    /** 时间格式 : MM-dd HH:mm */
    public static final String DATE_PATTERN_10 = "MM-dd HH:mm";
    
    
    /** 时间格式化格式 */
    public static final String DATA_FORMAT = "yyyyMMddHHmmssSSS";
    
    /**
     * 私有构造器
     */
    private DateTools()
    {
        
    }
    
    public static final int YEAR = 0;
    
    public static final int MONTH = 1;
    
    public static final int WEEK = 2;
    
    public static final int DAY = 3;
    
    public static final int HOUR = 4;
    
    public static final int MINUTE = 5;
    
    public static final int SECOND = 6;
    
    public static final int MILLISECOND = 7;
    
    public static final int MINUTEOFDAY = 8;
    
    /**
     * 在Locale下转换时间的格式为指定格式
     * 
     * @param time
     * @param oldpattern
     * @param dateTimeStyle DateFormat.LONG/DateFormat.FULL
     * @param currentLocale
     * @return
     */
    public static String getTimeByLocale(String time, String oldpattern, int dateTimeStyle, Locale currentLocale)
    {
        String oldtime = "";
        
        // 参数有效性检查
        if (null == oldpattern)
        {
            throw new IllegalArgumentException("the old pattern is null");
        }
        
        // 检查传入时间和格式是否一致
        SimpleDateFormat olddatepattern = new SimpleDateFormat(oldpattern, currentLocale);
        Date now = null;
        try
        {
            now = olddatepattern.parse(time);
            
            // 用原来的pattern解析日期，再和原来的比较，由此来检查时间是否合法
            oldtime = olddatepattern.format(now);
            if (!oldtime.equals(time))
            {
                throw new IllegalArgumentException("using Illegal time");
            }
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException("using [" + oldpattern + "] parse [" + time + "] failed");
        }
        
        // 格式转换
        DateFormat newdatepattern = DateFormat.getDateTimeInstance(dateTimeStyle, dateTimeStyle, currentLocale);
        return newdatepattern.format(now);
    }
    
    /**
     * 按要求转化时间的显示格式 参数：oldpattern，旧日期格式，如:yyyyMMddhhmmss 格式描述符的含义参见JDK simpleDateFormat类 newpattern，新日期格式
     */
    public static String timeTransform(String time, String oldpattern, String newpattern)
    {
        String oldtime = null;
        if (null == oldpattern || null == newpattern)
        {
            return time;
        }
        
        SimpleDateFormat olddatepattern = new SimpleDateFormat(oldpattern, new Locale("EN"));
        Date date = null;
        try
        {
            date = olddatepattern.parse(time);
            
            // 用原来的pattern解析日期，再和原来的比较，由此来检查时间是否合法
            oldtime = olddatepattern.format(date);
            if (!oldtime.equals(time))
            {
                return time;
            }
        }
        catch (ParseException e)
        {
            return time;
        }
        SimpleDateFormat newdatepattern = new SimpleDateFormat(newpattern, new Locale("EN"));
        
        return newdatepattern.format(date);
    }
    
    public static String timeTransform(String time, String newpattern)
    {
        SimpleDateFormat olddatepattern = new SimpleDateFormat(DATE_FORMAT_14, new Locale("EN"));
        Date date = null;
        try
        {
            date = olddatepattern.parse(time);
        
        }
        catch (ParseException e)
        {
            return time;
        }
        SimpleDateFormat newdatepattern = new SimpleDateFormat(newpattern, new Locale("EN"));
        
        return newdatepattern.format(date);
    }
    
    /**
     * 获取指定格式的当前日期 参数：pattern，日期格式，如:yyyyMMddhhmmss 格式描述符的含义参见JDK simpleDateFormat类
     */
    public static String getCurrentDate(String pattern)
    {
        if (null == pattern)
        {
            throw new IllegalArgumentException("input string parameter is null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("EN"));
        Date now = new Date();
        return sdf.format(now);
    }
    
    /**
     * 获取java.sql.Date类型的当前日期 返回：java.sql.Date 如2005-10-19
     */
    public static java.sql.Date getCurrentDate()
    {
        Calendar cal = Calendar.getInstance();
        return new java.sql.Date(cal.getTimeInMillis());
    }
    
    /**
     * 获取java.sql.Timestamp类型的当前日期。保护年月日时分秒 返回：java.sql.Timestamp 如2005-10-19 18:20:07
     */
    public static Timestamp getCurrentTimestamp()
    {
        Timestamp t = new Timestamp(new Date().getTime());
        return t;
    }
    
    public static long getCurrentTimeLong()
    {
        return new Date().getTime();
    }
    
    /**
     * 将日期长整型转换成字符串 参数：time，long，从格林威治时间：1970年1月1日0点起的毫秒数 pattern, String, 转换的目标格式
     */
    public static String long2TimeStr(long time, String pattern)
    {
        if (null == pattern)
        {
            throw new IllegalArgumentException("pattern parameter can not be null");
        }
        Date dt = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("EN"));
        return sdf.format(dt);
    }
    
    /**
     * 将日期型转换成字符串 参数：time，Date pattern, String, 转换的目标格式
     */
    public static String date2TimeStr(Date time, String pattern)
    {
        if (null == pattern)
        {
            throw new IllegalArgumentException("pattern parameter can not be null");
        }
        if (null == time)
        {
            throw new IllegalArgumentException("time parameter can not be null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("EN"));
        return sdf.format(time);
    }
    
    /**
     * 将日期增加一个增量，目前只能是，年，月，周，日，时、分、秒、毫秒 参数：date, long，原始时间 delta，int，增量的大小 unit, int, 增量的单位，YEAR, MONTH, DAY, HOUR,
     * MINUTE, SECOND, MILLISECOND 返回：long，从格林威治时间：1970年1月1日0点起的毫秒数
     */
    public static long addDate(long date, int delta, int unit)
    {
        if ((unit < YEAR) || (unit > MILLISECOND))
        {
            throw new IllegalArgumentException(
                "time unit must in [YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND, MILLISECOND], others not support");
        }
        Date dt = new Date(date);
        Calendar calendar = getLocalCalendar(dt);
        
        // 增加增量
        switch (unit)
        {
            case YEAR:
                calendar.add(Calendar.YEAR, delta);
                break;
            case MONTH:
                calendar.add(Calendar.MONTH, delta);
                break;
            case WEEK:
                calendar.add(Calendar.DAY_OF_WEEK, delta);
                break;
            case DAY:
                calendar.add(Calendar.DAY_OF_MONTH, delta);
                break;
            case HOUR:
                calendar.add(Calendar.HOUR, delta);
                break;
            case MINUTE:
                calendar.add(Calendar.MINUTE, delta);
                break;
            case SECOND:
                calendar.add(Calendar.SECOND, delta);
                break;
            case MILLISECOND:
                calendar.add(Calendar.MILLISECOND, delta);
                /* falls through */
            default:
                break;
        }
        
        // 获取新的时间，并转换成长整形
        Date ndt = calendar.getTime();
        return ndt.getTime();
    }
    
    /**
     * 将日期增加一个增量，目前只能是，年，月，周，日，时，分，秒，毫秒 参数：date, long，原始时间 delta，int，增量的大小 unit, int, 增量的单位，YEAR, MONTH, WEEK, DAY,
     * HOUR, MINUTE, SECOND, MILLISECOND pattern, String, 转换的目标格式 返回：String，指定格式的日期字符串
     */
    public static String addDate(long date, int delta, int unit, String pattern)
    {
        if (null == pattern)
        {
            throw new IllegalArgumentException("pattern parameter can not be null");
        }
        return long2TimeStr(addDate(date, delta, unit), pattern);
    }
    
    /**
     * 将字符串转换成日期长整形 参数：time，String，日期字符串 pattern, String, 解析的格式 返回：long，日期长整形
     */
    public static long timeStr2Long(String time, String pattern)
    {
        return timeStr2Date(time, pattern).getTime();
    }
    
    /**
     * 将字符串转换成日期形 参数：time，String，日期字符串 pattern, String, 解析的格式 返回：Date，日期形
     */
    public static Date timeStr2Date(String time, String pattern)
    {
        if (null == time)
        {
            throw new IllegalArgumentException("time parameter can not be null");
        }
        if (null == pattern)
        {
            throw new IllegalArgumentException("pattern parameter can not be null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, new Locale("EN"));
        try
        {
            return sdf.parse(time);
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException("using [" + pattern + "] parse [" + time + "] failed");
        }
    }
    
    /**
     * 获取日期字符串的某一部分 参数：date，有效的日期字符串 pattern，日期格式字符串
     * part，时间部分的指示符，只能是：YEAR,MONTH,WEEK,DAY,HOUR,MINUTE,SECOND，MILLISECOND
     */
    public static int getDatePart(String date, String pattern, int part)
    {
        if (null == date)
        {
            throw new IllegalArgumentException("date parameter is null");
        }
        if (null == pattern)
        {
            throw new IllegalArgumentException("pattern parameter is null");
        }
        if ((part < YEAR) || (part > MINUTEOFDAY))
        {
            throw new IllegalArgumentException("the part parameter must be in [YEAR,MONTH, DAY, HOUR, MINUTE, SECOND]");
        }
        Date dt = timeStr2Date(date, pattern);
        return getDatePart(dt, part);
    }
    
    /**
     * 获取日期的某一部分 参数：date，有效的日期类型 part，时间部分的指示符，只能是：YEAR,MONTH,WEEK,DAY,HOUR,MINUTE,SECOND，MILLISECOND
     */
    public static int getDatePart(Date date, int part)
    {
        if (null == date)
        {
            throw new IllegalArgumentException("date parameter is null");
        }
        if ((part < YEAR) || (part > MINUTEOFDAY))
        {
            throw new IllegalArgumentException("the part parameter must be in [YEAR,MONTH, DAY, HOUR, MINUTE, SECOND]");
        }
        Calendar calendar = getLocalCalendar(date);
        int result = 0;
        switch (part)
        {
            case YEAR:
                result = calendar.get(Calendar.YEAR);
                break;
            case MONTH:
                result = calendar.get(Calendar.MONTH);
                break;
            case WEEK:
                result = calendar.get(Calendar.DAY_OF_WEEK);
                break;
            case DAY:
                result = calendar.get(Calendar.DAY_OF_MONTH);
                break;
            case HOUR:
                result = calendar.get(Calendar.HOUR_OF_DAY);
                break;
            case MINUTE:
                result = calendar.get(Calendar.MINUTE);
                break;
            case SECOND:
                result = calendar.get(Calendar.SECOND);
                break;
            case MILLISECOND:
                result = calendar.get(Calendar.MILLISECOND);
                break;
            case MINUTEOFDAY:
                result = calendar.get(Calendar.HOUR_OF_DAY) * ONE_HOUR + calendar.get(Calendar.MINUTE);
                /* falls through */
            default:
                break;
        }
        return result;
    }
    
    /**
     * 获取下一个周期的开始时间 参数：date，String类型，有效的时间 pattern，String类型，时间格式字符串 part，int类型，周期类型，可以是年、月、日、周
     */
    public static String getNextPeriodTime(Date galeday, String pattern, int part)
    {
        if (null == galeday)
        {
            throw new IllegalArgumentException("date parameter is null");
        }
        if (null == pattern)
        {
            throw new IllegalArgumentException("pattern parameter is null");
        }
        if ((part < YEAR) || (part > DAY))
        {
            throw new IllegalArgumentException("the part parameter must be in [YEAR,MONTH, WEEK, DAY]");
        }
        String result = null;
        Calendar caldeduct = getLocalCalendar(galeday);
        Calendar calnow = getLocalCalendar(new Date());
        switch (part)
        {
            case DAY: // 扣费周期为每天
                return recursiveGet(caldeduct, calnow, pattern, Calendar.DAY_OF_MONTH, Calendar.HOUR, Calendar.HOUR);
            case WEEK: // 周期为每周
                return recursiveGetWeek(caldeduct,
                    calnow,
                    pattern,
                    Calendar.DAY_OF_WEEK,
                    Calendar.DAY_OF_MONTH,
                    0,
                    Calendar.DAY_OF_WEEK);
            case YEAR: // 周期为每年
                return recursiveGet(caldeduct, calnow, pattern, Calendar.YEAR, Calendar.MONTH, Calendar.MONTH);
            case MONTH: // 周期为每月
                return recursiveGet(caldeduct,
                    calnow,
                    pattern,
                    Calendar.MONTH,
                    Calendar.DAY_OF_MONTH,
                    Calendar.DAY_OF_MONTH);
            default:
                result = "unsupport period : " + String.valueOf(part);
                /* falls through */
                break;
        }
        return result;
    }
    
    private static String recursiveGetWeek(Calendar caldeduct, Calendar calnow, String pattern, int largepart,
        int part, int gap, int step)
    {
        int deduct = caldeduct.get(step);
        int now = calnow.get(step);
        if (step == Calendar.DAY_OF_WEEK)
        {
            gap = deduct - now;
        }
        if (deduct > now)
        {
            calnow.add(step, gap);
            return DateTools.date2TimeStr(calnow.getTime(), pattern);
        }
        else if (deduct < now)
        {
            calnow.add(step, 7 + gap);
            return DateTools.date2TimeStr(calnow.getTime(), pattern);
        }
        else
        {
            switch (step)
            {
                case Calendar.DAY_OF_WEEK:
                    step = Calendar.HOUR;
                    break;
                case Calendar.HOUR:
                    step = Calendar.MINUTE;
                    break;
                case Calendar.MINUTE:
                    step = Calendar.SECOND;
                    break;
                case Calendar.SECOND:
                    step = Calendar.MILLISECOND;
                    break;
                case Calendar.MILLISECOND:
                    return date2TimeStr(calnow.getTime(), pattern);
                default:
                    break;
            }
            return recursiveGetWeek(caldeduct, calnow, pattern, largepart, part, gap, step);
        }
    }
    
    private static String recursiveGet(Calendar caldeduct, Calendar calnow, String pattern, int largepart, int part,
        int step)
    {
        
        int deduct = caldeduct.get(step);
        int now = calnow.get(step);
        if (deduct > now)
        {
            calnow.set(part, caldeduct.get(part));
            if (largepart == Calendar.YEAR)
            {
                calnow.set(Calendar.DAY_OF_MONTH, caldeduct.get(Calendar.DAY_OF_MONTH));
            }
            return DateTools.date2TimeStr(calnow.getTime(), pattern);
        }
        else if (deduct < now)
        {
            calnow.add(largepart, 1);
            calnow.set(part, caldeduct.get(part));
            if (largepart == Calendar.YEAR)
            {
                calnow.set(Calendar.DAY_OF_MONTH, caldeduct.get(Calendar.DAY_OF_MONTH));
            }
            return DateTools.date2TimeStr(calnow.getTime(), pattern);
        }
        else
        {
            switch (step)
            {
                case Calendar.YEAR:
                    step = Calendar.MONTH;
                    break;
                case Calendar.MONTH:
                    step = Calendar.DATE;
                    break;
                case Calendar.DAY_OF_MONTH:
                    step = Calendar.HOUR;
                    break;
                case Calendar.HOUR:
                    step = Calendar.MINUTE;
                    break;
                case Calendar.MINUTE:
                    step = Calendar.SECOND;
                    break;
                case Calendar.SECOND:
                    step = Calendar.MILLISECOND;
                    break;
                case Calendar.MILLISECOND:
                    return date2TimeStr(calnow.getTime(), pattern);
                default:
                    break;
            }
            return recursiveGet(caldeduct, calnow, pattern, largepart, part, step);
        }
    }
    
    /**
     * 获得东八时区的日历，并设置日历的当前日期 参数：date，Date，日期型
     */
    public static Calendar getLocalCalendar(Date date)
    {
        // 设置为GMT+08:00时区
        String[] ids = TimeZone.getAvailableIDs(8 * ONE_HOUR * ONE_MINUTE * ONE_THOUSAND);
        if (0 == ids.length)
        {
            throw new IllegalArgumentException("get id of GMT+08:00 time zone failed");
        }
        // 创建Calendar对象，并设置为指定时间
        Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
        
        // 设置成宽容方式
        if (!calendar.isLenient())
        {
            calendar.setLenient(true);
        }
        // 设置SUNDAY为每周的第一天
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        
        // 设置日历的当前时间
        calendar.setTime(date);
        return calendar;
    }
    
    /**
     * 获取当月的开始时间 <功能详细描述>
     * 
     * @param date
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Date getBeginTimeOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //calendar.add(Calendar.MONTH, -1);
        
        // 当月第1天日期
        Date firstDate = calendar.getTime();
        return firstDate;
    }
    
    /**
     * 获取当月的结束时间 <功能详细描述>
     * 
     * @param date
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Date getEndTimeOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(date); 
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        // 当月最后一天日期
        Date lastDate = calendar.getTime();
        return lastDate;
    }
    
    public static enum CompareDateFormate
    {
        year, month, day, hour, minute, second,

        yyyyMMddhhmmss, yyyyMMddhhmm, yyyyMMddhh, yyyyMMdd, yyyyMM,

        MMddhhmmss, MMddhhmm, MMddhh, MMdd, ddhhmmss, ddhhmm, ddhh, hhmmss, hhmm, mmss
    }
    
    private final static HashMap<CompareDateFormate, int[]> map = new HashMap<CompareDateFormate, int[]>();
    static
    {
        map.put(CompareDateFormate.year, new int[] {Calendar.YEAR});
        map.put(CompareDateFormate.month, new int[] {Calendar.MONTH});
        map.put(CompareDateFormate.day, new int[] {Calendar.DATE});
        map.put(CompareDateFormate.hour, new int[] {Calendar.HOUR_OF_DAY});
        map.put(CompareDateFormate.minute, new int[] {Calendar.MINUTE});
        map.put(CompareDateFormate.second, new int[] {Calendar.SECOND});
        
        map.put(CompareDateFormate.yyyyMMddhhmmss, new int[] {Calendar.YEAR, Calendar.MONTH, Calendar.DATE,
            Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND});
        map.put(CompareDateFormate.yyyyMMddhhmm, new int[] {Calendar.YEAR, Calendar.MONTH, Calendar.DATE,
            Calendar.HOUR_OF_DAY, Calendar.MINUTE});
        map.put(CompareDateFormate.yyyyMMddhh, new int[] {Calendar.YEAR, Calendar.MONTH, Calendar.DATE,
            Calendar.HOUR_OF_DAY});
        map.put(CompareDateFormate.yyyyMMdd, new int[] {Calendar.YEAR, Calendar.MONTH, Calendar.DATE});
        map.put(CompareDateFormate.yyyyMM, new int[] {Calendar.YEAR, Calendar.MONTH});
        
        map.put(CompareDateFormate.MMddhhmmss, new int[] {Calendar.MONTH, Calendar.DATE, Calendar.HOUR_OF_DAY,
            Calendar.MINUTE, Calendar.SECOND});
        map.put(CompareDateFormate.MMddhhmm, new int[] {Calendar.MONTH, Calendar.DATE, Calendar.HOUR_OF_DAY,
            Calendar.MINUTE});
        map.put(CompareDateFormate.MMddhh, new int[] {Calendar.MONTH, Calendar.DATE, Calendar.HOUR_OF_DAY});
        map.put(CompareDateFormate.MMdd, new int[] {Calendar.MONTH, Calendar.DATE});
        
        map.put(CompareDateFormate.ddhhmmss, new int[] {Calendar.DATE, Calendar.HOUR_OF_DAY, Calendar.MINUTE,
            Calendar.SECOND});
        map.put(CompareDateFormate.ddhhmm, new int[] {Calendar.DATE, Calendar.HOUR_OF_DAY, Calendar.MINUTE});
        map.put(CompareDateFormate.ddhh, new int[] {Calendar.DATE, Calendar.HOUR_OF_DAY});
        
        map.put(CompareDateFormate.hhmmss, new int[] {Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND});
        map.put(CompareDateFormate.hhmm, new int[] {Calendar.HOUR_OF_DAY, Calendar.MINUTE});
        map.put(CompareDateFormate.mmss, new int[] {Calendar.MINUTE, Calendar.SECOND});
    }
    
    /**
     * 根据CompareFields的格式（如只比较年月）比较两个日期先后，
     * 
     * 在比较字段内，若返回1，表示date1在date2之后，返回-1，表示date1在date2之前，0表示两者相等
     */
    public static int compare(Date date1, Date date2, CompareDateFormate cdf)
    {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        
        int[] form = map.get(cdf);
        for (int field : form)
        {
            int t1 = c1.get(field);
            int t2 = c2.get(field);
            if (t1 > t2)
            {
                return 1;
            }
            else if (t1 < t2)
            {
                return -1;
            }
        }
        
        return 0;
    }
    
    /**
     * 计算传入时间到（dept+传入时间）的剩余时间
     * 
     * @param minute 分钟
     * @param second 秒
     * @param dept 往后累加的分钟数
     * @return
     */
    public static String[] getSpareTime(int minute, int second, int dept)
    {
        String[] result = new String[2];
        
        // 一分钟60秒用于计算剩余秒数
        int total = 60;
        int temMin = 0;
        int temSec = 0;
        
        // 只有处在当前时间 与 当前时间+累加的分钟 之间的，才计算剩余时间
        if (dept > minute)
        {
            if (second == 0)
            {
                temMin = dept - minute;
                temSec = second;//second==0
            }
            else
            {
                temMin = dept - minute - 1;
                temSec = total - second;
            }
        }
        result[0] = (temMin < 10) ? "0" + temMin : String.valueOf(temMin);
        result[1] = (temSec < 10) ? "0" + temSec : String.valueOf(temSec);
        return result;
    }
    
    /**
     * 计算时间差 <功能详细描述>
     * 
     * @param startTime
     * @param endTime
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static long getTimeDifference(long startTime, long endTime)
    {
        if ( endTime > startTime )
        {
            return endTime - startTime;
        }
        else
        {
            return 0;
        }
    }
     
    /**
     * 格式化成系统常用日期格式：yyyy-MM-dd HH:mm:ss
     */
    public static String format(java.util.Date date)
    {
        SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT_24HOUR_19);
        if(null != date)
        {
            return sf.format(date);
        }
        return null;
    }
    
    /**
     * 格式化日期
     */
    public static String format(Date date, String formatStr)
    {
        if (date == null)
        {
            return null;
        }
        
        SimpleDateFormat sf = new SimpleDateFormat(formatStr);
        return sf.format(date);
    }
    
    /**
     * 获取下个月开始时刻
     */
    public static Date getBeginOfNextMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // 月份加1
        c.add(Calendar.MONTH, 1);

        return new Date(c.getTimeInMillis());
    }
    
    /**
     * 获取UTC时间<一句话功能简述> <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Date getUTCTime()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(cal.getTimeInMillis() - TimeZone.getDefault().getRawOffset());
        return new Date(cal.getTimeInMillis());
    }
    
    /**
     * 获取指定日期的一周起始时间
     * 
     * @param dt
     * @return
     */
    public static Timestamp getBeginOfCurWeek(Date dt)
    {
        StringBuffer beginDay = new StringBuffer();

        Calendar calendar = getCalForWeek(dt);

        int weekx = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekx == 1)
        {
            weekx = 8;
        }

        calendar.add(Calendar.DAY_OF_WEEK, 2 - weekx);

        beginDay.append(String.valueOf(calendar.get(Calendar.YEAR)));
        // 小于10在月份前添0
        if ((calendar.get(Calendar.MONTH) + 1) < 10)
        {
            beginDay.append("0").append((calendar.get(Calendar.MONTH) + 1));
        }
        else
        {
            beginDay.append((calendar.get(Calendar.MONTH) + 1));
        }
        // 小于10在日期前添0
        if ((calendar.get(Calendar.DAY_OF_MONTH) - 1) < 10)
        {
            beginDay.append("0").append(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        }
        else
        {
            beginDay.append(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        }
        beginDay.append("000000");

        return parse(beginDay.toString(), "yyyyMMddHHmmss");

    }

    /**
     * 获取指定日期的一周结束时间
     * 
     * @param dt
     * @return
     */
    public static Timestamp getEndOfCurWeek(Date dt)
    {
        Calendar calendar = getCalForWeek(dt);

        StringBuffer endDay = new StringBuffer();

        int weekx = calendar.get(Calendar.DAY_OF_WEEK);
        if (weekx == 1)
        {
            weekx = 8;
        }

        calendar.add(Calendar.DAY_OF_WEEK, 2 - weekx);
        calendar.add(Calendar.DAY_OF_MONTH, 6);

        endDay.append(String.valueOf(calendar.get(Calendar.YEAR)));
        // 小于10在月份前添0
        if ((calendar.get(Calendar.MONTH) + 1) < 10)
        {
            endDay.append("0").append((calendar.get(Calendar.MONTH) + 1));
        }
        else
        {
            endDay.append((calendar.get(Calendar.MONTH) + 1));
        }
        // 小于10在日期前添0
        if ((calendar.get(Calendar.DAY_OF_MONTH) - 1) < 10)
        {
            endDay.append("0").append(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        }
        else
        {
            endDay.append(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        }
        endDay.append("235959");

        return parse(endDay.toString(), "yyyyMMddHHmmss");
    }
    
    /**
     * 获取本周开始时间
     * 
     * @param dt 当前时间
     * @return 本周开始时间
     */
    // modify by kf56385 at 2011-11-14 for IRD-21056 begin
    private static Calendar getCalForWeek(Date dt)
    {
        Calendar calendar = null;
        String dateStr = format(dt, "yyyyMMddHHmmss");
        Integer year = Integer.valueOf(dateStr.substring(0, 4));
        Integer month = Integer.valueOf(dateStr.substring(4, 6));
        Integer day = Integer.valueOf(dateStr.substring(6, 8));

        calendar = new GregorianCalendar(year.intValue(), (month.intValue() - 1), day.intValue() + 1);
        return calendar;
    }
    
    /**
     * 根据指定的格式将指定的日期字符串转换为Timestamp类型
     * 
     * @author coder
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static Timestamp parse(String dateStr, String formatStr)
    {
        if (null == dateStr)
        {
            return null;
        }

        Date date = null;
        SimpleDateFormat sf = new SimpleDateFormat(formatStr);
        try
        {
            date = sf.parse(dateStr);
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException("date format error");
        }

        if (null != date)
        {
            return new Timestamp(date.getTime());
        }
        else
        {
            return null;
        }
    }
    
    /**
     * 获取当天开始时间
     * 
     * @param date
     * @return [参数说明]
     * 
     * @return Timestamp [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Timestamp getBeginDate(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return new Timestamp(c.getTimeInMillis());
    }
    
    /**
     * 获取当天结束时间
     * 
     * @param date
     * @return [参数说明]
     * 
     * @return Timestamp [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Timestamp getEndDate(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);

        return new Timestamp(c.getTimeInMillis());
    }
    
    /**
     * 格式化为 Timestamp
     * 
     * @param dateStr
     * @param formater
     * @return [参数说明]
     * 
     * @return Timestamp [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Timestamp formTimestamp(String dateStr, String formater)
    {
        return new Timestamp(formdate(dateStr,formater).getTime());
    }
    
    /**
     * 把字符串格式化日期
     */
    public static Date formdate(String dateStr, String formater)
    {
        formater = (StringTools.isEmpty(formater)) ? "yyyy-MM-dd HH:mm:ss" : formater;
        DateFormat formatter = new SimpleDateFormat(formater);
        Date date = null;
        try
        {
            date = formatter.parse(dateStr);
        }
        catch (ParseException e)
        {
            // e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 获取下个月开始时刻（该时刻根据配置得来）
     */
    public static Timestamp getBeginOfNextMonth_Timestamp(Date date)
    {
        /** 每月的开始日期，默认为自然月开始，即每月的1号 */
        int beginDay = 1;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, beginDay);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // 月份加1
        c.add(Calendar.MONTH, 1);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 获取当月开始时间
     */
    public static Timestamp getBeginOfCurrentMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return new Timestamp(c.getTimeInMillis());
    }
    
    /**
     * 获取当月最后一天23时59分59秒 <功能详细描述>
     * 
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Date getEndTimeOfMonth2(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(date); 
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.SECOND, -1);
        // 当月最后一天日期
        Date lastDate = calendar.getTime();
        return lastDate;
    }
    
    
    /**
     * <一句话功能简述>生成事务标识 <功能详细描述>
     * 
     * @return [事务标识] 格式yyyyMMddHHmmssXXXXX(19位) 前16位由时间生成 后3位为本机IP后三位，不足前补0
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getTransactTid()
    {
        StringBuilder sb = new StringBuilder();
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        String tidPre = sdf.format(date);
        tidPre = tidPre.substring(0, 16);
        sb.append(tidPre);// 前16位由时间生成
        String lastIP = "255";
        sb.append(lastIP);
        return sb.toString();
    }
    
    /**
     * 判断当前时间是否在有效期内
     * 
     * @param beginTimeStr
     * @param endTimeStr
     * 
     * @return boolean [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isInEffectiveTime(String beginTimeStr, String endTimeStr, String dateFormat,
        CompareDateFormate compareDateFormate)
    {
        boolean compareResult = false;
        
        // 开始月份
        Date beginMonth = timeStr2Date(beginTimeStr, dateFormat);
        
        // 结束月份
        Date endMonth = timeStr2Date(endTimeStr, dateFormat);
        
        // 当前月份
        Date nowMonth = timeStr2Date(DateTools.getCurrentDate(dateFormat), dateFormat);
        
        int isLaterThanBeginMonth = compare(nowMonth, beginMonth, compareDateFormate);
        
        int isEarlierThanEndMonth = compare(endMonth, nowMonth, compareDateFormate);
        
        // 如果当前月份在开始月份和结束月份之间，返回有效。
        if (isLaterThanBeginMonth >= 0 && isEarlierThanEndMonth > 0)
        {
            compareResult = true;
        }
        
        return compareResult;
    }
}
