package org.mywap.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.mywap.util.DateTools.CompareDateFormate;

/**
 * 依赖于datetools
 * @author s
 *
 */
public class DateUtil
{
    private static final Logger log = Logger.getLogger(DateUtil.class);
    
    public static final String HOR_DAY_FORMAT = "yyyy-MM-dd";// 带连字符"-"精确到天的字符串格式
    
    public static final String YYYYMMDDHHMISS = "yyyyMMddHHmmss";
    
    public static final String YYYYMMDD = "yyyyMMdd";
    
    public static final String YYYYMMDDHH = "yyyy-MM-dd HH:mm";
    
    public static final String YYYYlMMlDD = "yyyy/MM/dd HH:mm";
    
    public static final String HOR_SEC_FORMAT = "yyyy-MM-dd HH:mm:ss";// 带连字符"-"精确到时间的字符串格式
    
    /**
     * 计算时间单位: 分钟
     */
    public static final int UNIT_MINUTE = 1;
    
    /**
     * 计算时间单位: 小时
     */
    public static final int UNIT_HOUR = 2;
    
    /**
     * 计算时间单位: 天
     */
    public static final int UNIT_DAY = 3;
    
    /**
     * 计算时间单位: 月
     */
    public static final int UNIT_MONTH = 4;

    /**
     * 得到当前时间的字符格式
     */
    public static Timestamp getCurrentTimeStamp()
    {
        String currentDate = format(new Date(), HOR_DAY_FORMAT);
        return parse(currentDate, HOR_DAY_FORMAT);
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
     * 根据指定的格式将指定的日期字符串转换为Timestamp类型 <功能详细描述>
     * 
     * @param dateStr
     * @param formatStr
     * @return [参数说明]
     * 
     * @return Timestamp [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
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
            log.error(String.format("date format error [date : %s, format : %s]", dateStr, formatStr), e);
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
     * 得到当前时间
     */
    public static Timestamp getCurrentDatetime()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * <返回指定时间对应的字符串> <功能详细描述>
     * 
     * @param date
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String formatToYYYY_MM_DD(Date date)
    {
        return format(date, HOR_DAY_FORMAT);
    }
    
    public static String formatToYYYYMMDDHHMISS(Date date)
    {
        return format(date, YYYYMMDDHHMISS);
    }
    
    public static String formatToYYYYMMDD(Date date)
    {
        return format(date, YYYYMMDD);
    }
    
    public static String formatToYYYYMMDDHH(Date date)
    {
        return format(date, YYYYMMDDHH);
    }
    
    /**
     * yyyy/mm/dd HH:mm
     * 
     * @param date
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String formatToYYYYlMMlDD_HHmm(Date date)
    {
        return format(date, YYYYlMMlDD);
    }
    
    /**
     * 计算时间
     * 
     * @param date 需要计算的时间
     * @param amount 量
     * @param unit 单位
     * @return [参数说明]
     * 
     * @return Date 计算的后的时间
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Date computeDate(Date date, int amount, int unit)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch (unit)
        {
            case UNIT_MINUTE:
                calendar.add(Calendar.MINUTE, amount);
                break;
            case UNIT_HOUR:
                calendar.add(Calendar.HOUR, amount);
                break;
            case UNIT_DAY:
                calendar.add(Calendar.DAY_OF_MONTH, amount);
                break;
            case UNIT_MONTH:
                calendar.add(Calendar.MONTH, amount);
                break;
        }
        return calendar.getTime();
    }
    
    public static Date addDay(Date d, double days)
    {
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);

        final long DAY_TIME = (24 * 60 * 60 * 1000);
        long milliseconds = (long) (DAY_TIME * days);

        ca.add(Calendar.DAY_OF_MONTH, (int) (milliseconds / DAY_TIME));
        ca.add(Calendar.MILLISECOND, (int) (milliseconds % DAY_TIME));

        return ca.getTime();
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
        return new Timestamp(formdate(dateStr, formater).getTime());
    }
    
    /**
     * 把字符串格式化日期
     */
    public static Date formdate(String dateStr, String formater)
    {
        formater = (null == formater) ? "yyyy-MM-dd HH:mm:ss" : formater;
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
    
    public static Date addSecond(Date d, long seconds)
    {
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);

        final long DAY_TIME = (24 * 60 * 60);

        ca.add(Calendar.DAY_OF_MONTH, (int) (seconds / DAY_TIME));
        ca.add(Calendar.SECOND, (int) (seconds % DAY_TIME));

        return ca.getTime();
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
    
    public static void main(String[] args)
    {
        log.debug(format(formdate("2016-4-8.10.51. 20. 0", "yyyy-MM-dd.HH.mm.ss"), YYYYMMDDHHMISS));
    }
}
