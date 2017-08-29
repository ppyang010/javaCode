package org.mywap.util;


/*
 * �� �� ��:  DateTools.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  zkf56839
 * �޸�ʱ��:  Mar 16, 2012
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
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
 * <һ�仰���ܼ���> <������ϸ����>
 * 
 * @author zKF56839
 * @version [�汾��, Mar 16, 2012]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class DateTools
{
    /** Automatically generated javadoc for: ONE_MINUTE */
    private static final int ONE_MINUTE = 60;
    
    /** Automatically generated javadoc for: ONE_THOUSAND */
    private static final int ONE_THOUSAND = 1000;
    
    /** Automatically generated javadoc for: ONE_HOUR */
    private static final int ONE_HOUR = 60;
    
    /** ʱ���ʽ : yyyy/MM/dd HH:mm */
    public static final String PATTERN = "yyyy/MM/dd HH:mm";
    
    /** ʱ���ʽ : yyyyMMddHHmm */
    public static final String DATE_FORMAT_12 = "yyyyMMddHHmm";
    
    /** ʱ���ʽ : yyyyMMddHHmmss */
    public static final String DATE_FORMAT_14 = "yyyyMMddHHmmss";
    
    /** ʱ���ʽ : yyyyMM */
    public static final String DATE_FORMAT_6 = "yyyyMM";
    
    /** ʱ���ʽ : yyyy-MM */
    public static final String DATE_FORMAT_7 = "yyyy-MM";
    
    /** ʱ���ʽ : yyyyMMdd */
    public static final String DATE_FORMAT2_8 = "yyyyMMdd";
    
    /** ʱ���ʽ : yy.MM.dd */
    public static final String DATE_FORMAT_8 = "yy.MM.dd";
    
    /** ʱ���ʽ : [yyyy-MM-dd hh:mm:ss] */
    public static final String DATE_FORMAT_21 = "[yyyy-MM-dd hh:mm:ss]";
    
    /** ʱ���ʽ : [yyyy-MM-dd HH:mm:ss] */
    public static final String DATE_FORMAT_24HOUR_21 = "[yyyy-MM-dd HH:mm:ss]";
    
    /** ʱ���ʽ ��yyyy-MM-dd hh:mm */
    public static final String DATE_FORMAT_16 = "yyyy-MM-dd hh:mm";
    
    /** ʱ���ʽ : yy-MM-dd hh:mm */
    public static final String DATE_PATTERN_14 = "yy-MM-dd hh:mm";
    
    /** ʱ���ʽ : yy-MM-dd hh:mm */
    public static final String DATE_FORMAT_24HOUR_16 = "yyyy-MM-dd HH:mm";
    
    /** ʱ���ʽ : yy-MM-dd HH:mm */
    public static final String DATE_PATTERN_24HOUR_14 = "yy-MM-dd HH:mm";
    
    /** ʱ���ʽ ��yyyy-MM-dd */
    public static final String DATE_FORMAT_10 = "yyyy-MM-dd";
    
    public static final String DATE_FORMAT_18 = "yyyy��MM��";
    
    public static final String DATE_FORMAT_YEAR = "yyyy";
    
    public static final String DATE_FORMAT_MONTH="MM";
    
    public static final String DATE_FORMAT_DAY="dd";
    
    /** ʱ���ʽ : yyyy-MM-dd HH:mm:ss */
    public static final String DATE_FORMAT_24HOUR_19 = "yyyy-MM-dd HH:mm:ss";
    
    /** ʱ���ʽ : MM-dd hh:mm */
    public static final String DATE_PATTERN_8 = "MM-dd hh:mm";
    
    /** ʱ���ʽ : MM-dd HH:mm */
    public static final String DATE_PATTERN_10 = "MM-dd HH:mm";
    
    
    /** ʱ���ʽ����ʽ */
    public static final String DATA_FORMAT = "yyyyMMddHHmmssSSS";
    
    /**
     * ˽�й�����
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
     * ��Locale��ת��ʱ��ĸ�ʽΪָ����ʽ
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
        
        // ������Ч�Լ��
        if (null == oldpattern)
        {
            throw new IllegalArgumentException("the old pattern is null");
        }
        
        // ��鴫��ʱ��͸�ʽ�Ƿ�һ��
        SimpleDateFormat olddatepattern = new SimpleDateFormat(oldpattern, currentLocale);
        Date now = null;
        try
        {
            now = olddatepattern.parse(time);
            
            // ��ԭ����pattern�������ڣ��ٺ�ԭ���ıȽϣ��ɴ������ʱ���Ƿ�Ϸ�
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
        
        // ��ʽת��
        DateFormat newdatepattern = DateFormat.getDateTimeInstance(dateTimeStyle, dateTimeStyle, currentLocale);
        return newdatepattern.format(now);
    }
    
    /**
     * ��Ҫ��ת��ʱ�����ʾ��ʽ ������oldpattern�������ڸ�ʽ����:yyyyMMddhhmmss ��ʽ�������ĺ���μ�JDK simpleDateFormat�� newpattern�������ڸ�ʽ
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
            
            // ��ԭ����pattern�������ڣ��ٺ�ԭ���ıȽϣ��ɴ������ʱ���Ƿ�Ϸ�
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
     * ��ȡָ����ʽ�ĵ�ǰ���� ������pattern�����ڸ�ʽ����:yyyyMMddhhmmss ��ʽ�������ĺ���μ�JDK simpleDateFormat��
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
     * ��ȡjava.sql.Date���͵ĵ�ǰ���� ���أ�java.sql.Date ��2005-10-19
     */
    public static java.sql.Date getCurrentDate()
    {
        Calendar cal = Calendar.getInstance();
        return new java.sql.Date(cal.getTimeInMillis());
    }
    
    /**
     * ��ȡjava.sql.Timestamp���͵ĵ�ǰ���ڡ�����������ʱ���� ���أ�java.sql.Timestamp ��2005-10-19 18:20:07
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
     * �����ڳ�����ת�����ַ��� ������time��long���Ӹ�������ʱ�䣺1970��1��1��0����ĺ����� pattern, String, ת����Ŀ���ʽ
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
     * ��������ת�����ַ��� ������time��Date pattern, String, ת����Ŀ���ʽ
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
     * ����������һ��������Ŀǰֻ���ǣ��꣬�£��ܣ��գ�ʱ���֡��롢���� ������date, long��ԭʼʱ�� delta��int�������Ĵ�С unit, int, �����ĵ�λ��YEAR, MONTH, DAY, HOUR,
     * MINUTE, SECOND, MILLISECOND ���أ�long���Ӹ�������ʱ�䣺1970��1��1��0����ĺ�����
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
        
        // ��������
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
        
        // ��ȡ�µ�ʱ�䣬��ת���ɳ�����
        Date ndt = calendar.getTime();
        return ndt.getTime();
    }
    
    /**
     * ����������һ��������Ŀǰֻ���ǣ��꣬�£��ܣ��գ�ʱ���֣��룬���� ������date, long��ԭʼʱ�� delta��int�������Ĵ�С unit, int, �����ĵ�λ��YEAR, MONTH, WEEK, DAY,
     * HOUR, MINUTE, SECOND, MILLISECOND pattern, String, ת����Ŀ���ʽ ���أ�String��ָ����ʽ�������ַ���
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
     * ���ַ���ת�������ڳ����� ������time��String�������ַ��� pattern, String, �����ĸ�ʽ ���أ�long�����ڳ�����
     */
    public static long timeStr2Long(String time, String pattern)
    {
        return timeStr2Date(time, pattern).getTime();
    }
    
    /**
     * ���ַ���ת���������� ������time��String�������ַ��� pattern, String, �����ĸ�ʽ ���أ�Date��������
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
     * ��ȡ�����ַ�����ĳһ���� ������date����Ч�������ַ��� pattern�����ڸ�ʽ�ַ���
     * part��ʱ�䲿�ֵ�ָʾ����ֻ���ǣ�YEAR,MONTH,WEEK,DAY,HOUR,MINUTE,SECOND��MILLISECOND
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
     * ��ȡ���ڵ�ĳһ���� ������date����Ч���������� part��ʱ�䲿�ֵ�ָʾ����ֻ���ǣ�YEAR,MONTH,WEEK,DAY,HOUR,MINUTE,SECOND��MILLISECOND
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
     * ��ȡ��һ�����ڵĿ�ʼʱ�� ������date��String���ͣ���Ч��ʱ�� pattern��String���ͣ�ʱ���ʽ�ַ��� part��int���ͣ��������ͣ��������ꡢ�¡��ա���
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
            case DAY: // �۷�����Ϊÿ��
                return recursiveGet(caldeduct, calnow, pattern, Calendar.DAY_OF_MONTH, Calendar.HOUR, Calendar.HOUR);
            case WEEK: // ����Ϊÿ��
                return recursiveGetWeek(caldeduct,
                    calnow,
                    pattern,
                    Calendar.DAY_OF_WEEK,
                    Calendar.DAY_OF_MONTH,
                    0,
                    Calendar.DAY_OF_WEEK);
            case YEAR: // ����Ϊÿ��
                return recursiveGet(caldeduct, calnow, pattern, Calendar.YEAR, Calendar.MONTH, Calendar.MONTH);
            case MONTH: // ����Ϊÿ��
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
     * ��ö���ʱ���������������������ĵ�ǰ���� ������date��Date��������
     */
    public static Calendar getLocalCalendar(Date date)
    {
        // ����ΪGMT+08:00ʱ��
        String[] ids = TimeZone.getAvailableIDs(8 * ONE_HOUR * ONE_MINUTE * ONE_THOUSAND);
        if (0 == ids.length)
        {
            throw new IllegalArgumentException("get id of GMT+08:00 time zone failed");
        }
        // ����Calendar���󣬲�����Ϊָ��ʱ��
        Calendar calendar = new GregorianCalendar(TimeZone.getDefault());
        
        // ���óɿ��ݷ�ʽ
        if (!calendar.isLenient())
        {
            calendar.setLenient(true);
        }
        // ����SUNDAYΪÿ�ܵĵ�һ��
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        
        // ���������ĵ�ǰʱ��
        calendar.setTime(date);
        return calendar;
    }
    
    /**
     * ��ȡ���µĿ�ʼʱ�� <������ϸ����>
     * 
     * @param date
     * @return [����˵��]
     * 
     * @return Date [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static Date getBeginTimeOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //calendar.add(Calendar.MONTH, -1);
        
        // ���µ�1������
        Date firstDate = calendar.getTime();
        return firstDate;
    }
    
    /**
     * ��ȡ���µĽ���ʱ�� <������ϸ����>
     * 
     * @param date
     * @return [����˵��]
     * 
     * @return Date [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static Date getEndTimeOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(date); 
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        // �������һ������
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
     * ����CompareFields�ĸ�ʽ����ֻ�Ƚ����£��Ƚ����������Ⱥ�
     * 
     * �ڱȽ��ֶ��ڣ�������1����ʾdate1��date2֮�󣬷���-1����ʾdate1��date2֮ǰ��0��ʾ�������
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
     * ���㴫��ʱ�䵽��dept+����ʱ�䣩��ʣ��ʱ��
     * 
     * @param minute ����
     * @param second ��
     * @param dept �����ۼӵķ�����
     * @return
     */
    public static String[] getSpareTime(int minute, int second, int dept)
    {
        String[] result = new String[2];
        
        // һ����60�����ڼ���ʣ������
        int total = 60;
        int temMin = 0;
        int temSec = 0;
        
        // ֻ�д��ڵ�ǰʱ�� �� ��ǰʱ��+�ۼӵķ��� ֮��ģ��ż���ʣ��ʱ��
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
     * ����ʱ��� <������ϸ����>
     * 
     * @param startTime
     * @param endTime
     * @return
     * @see [�ࡢ��#��������#��Ա]
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
     * ��ʽ����ϵͳ�������ڸ�ʽ��yyyy-MM-dd HH:mm:ss
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
     * ��ʽ������
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
     * ��ȡ�¸��¿�ʼʱ��
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

        // �·ݼ�1
        c.add(Calendar.MONTH, 1);

        return new Date(c.getTimeInMillis());
    }
    
    /**
     * ��ȡUTCʱ��<һ�仰���ܼ���> <������ϸ����>
     * 
     * @return [����˵��]
     * 
     * @return Date [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static Date getUTCTime()
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(cal.getTimeInMillis() - TimeZone.getDefault().getRawOffset());
        return new Date(cal.getTimeInMillis());
    }
    
    /**
     * ��ȡָ�����ڵ�һ����ʼʱ��
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
        // С��10���·�ǰ��0
        if ((calendar.get(Calendar.MONTH) + 1) < 10)
        {
            beginDay.append("0").append((calendar.get(Calendar.MONTH) + 1));
        }
        else
        {
            beginDay.append((calendar.get(Calendar.MONTH) + 1));
        }
        // С��10������ǰ��0
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
     * ��ȡָ�����ڵ�һ�ܽ���ʱ��
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
        // С��10���·�ǰ��0
        if ((calendar.get(Calendar.MONTH) + 1) < 10)
        {
            endDay.append("0").append((calendar.get(Calendar.MONTH) + 1));
        }
        else
        {
            endDay.append((calendar.get(Calendar.MONTH) + 1));
        }
        // С��10������ǰ��0
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
     * ��ȡ���ܿ�ʼʱ��
     * 
     * @param dt ��ǰʱ��
     * @return ���ܿ�ʼʱ��
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
     * ����ָ���ĸ�ʽ��ָ���������ַ���ת��ΪTimestamp����
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
     * ��ȡ���쿪ʼʱ��
     * 
     * @param date
     * @return [����˵��]
     * 
     * @return Timestamp [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * ��ȡ�������ʱ��
     * 
     * @param date
     * @return [����˵��]
     * 
     * @return Timestamp [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * ��ʽ��Ϊ Timestamp
     * 
     * @param dateStr
     * @param formater
     * @return [����˵��]
     * 
     * @return Timestamp [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static Timestamp formTimestamp(String dateStr, String formater)
    {
        return new Timestamp(formdate(dateStr,formater).getTime());
    }
    
    /**
     * ���ַ�����ʽ������
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
     * ��ȡ�¸��¿�ʼʱ�̣���ʱ�̸������õ�����
     */
    public static Timestamp getBeginOfNextMonth_Timestamp(Date date)
    {
        /** ÿ�µĿ�ʼ���ڣ�Ĭ��Ϊ��Ȼ�¿�ʼ����ÿ�µ�1�� */
        int beginDay = 1;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DATE, beginDay);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        // �·ݼ�1
        c.add(Calendar.MONTH, 1);

        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * ��ȡ���¿�ʼʱ��
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
     * ��ȡ�������һ��23ʱ59��59�� <������ϸ����>
     * 
     * @param date
     * @return
     * @see [�ࡢ��#��������#��Ա]
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
        // �������һ������
        Date lastDate = calendar.getTime();
        return lastDate;
    }
    
    
    /**
     * <һ�仰���ܼ���>���������ʶ <������ϸ����>
     * 
     * @return [�����ʶ] ��ʽyyyyMMddHHmmssXXXXX(19λ) ǰ16λ��ʱ������ ��3λΪ����IP����λ������ǰ��0
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static String getTransactTid()
    {
        StringBuilder sb = new StringBuilder();
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        String tidPre = sdf.format(date);
        tidPre = tidPre.substring(0, 16);
        sb.append(tidPre);// ǰ16λ��ʱ������
        String lastIP = "255";
        sb.append(lastIP);
        return sb.toString();
    }
    
    /**
     * �жϵ�ǰʱ���Ƿ�����Ч����
     * 
     * @param beginTimeStr
     * @param endTimeStr
     * 
     * @return boolean [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static boolean isInEffectiveTime(String beginTimeStr, String endTimeStr, String dateFormat,
        CompareDateFormate compareDateFormate)
    {
        boolean compareResult = false;
        
        // ��ʼ�·�
        Date beginMonth = timeStr2Date(beginTimeStr, dateFormat);
        
        // �����·�
        Date endMonth = timeStr2Date(endTimeStr, dateFormat);
        
        // ��ǰ�·�
        Date nowMonth = timeStr2Date(DateTools.getCurrentDate(dateFormat), dateFormat);
        
        int isLaterThanBeginMonth = compare(nowMonth, beginMonth, compareDateFormate);
        
        int isEarlierThanEndMonth = compare(endMonth, nowMonth, compareDateFormate);
        
        // �����ǰ�·��ڿ�ʼ�·ݺͽ����·�֮�䣬������Ч��
        if (isLaterThanBeginMonth >= 0 && isEarlierThanEndMonth > 0)
        {
            compareResult = true;
        }
        
        return compareResult;
    }
}
