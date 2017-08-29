package org.mywap.util.date;

import java.text.ParseException;  
import java.util.ArrayList;  
import java.util.Calendar;  
import java.util.Date;  
import java.util.GregorianCalendar;  
import java.util.List;  
  
import org.apache.commons.lang3.time.DateFormatUtils;  
import org.apache.commons.lang3.time.DateUtils;  
  
/** 
 * @ClassName:MyDateUtils 
 * @Description:����commons lang time������ڲ����๤�ߡ� 
 * @Author:hankaibo 
 * @date:2013-8-18 
 * @UpdateUser:hankaibo 
 * @UpdateDate:2013-8-18 ����10:36:19 
 * @UpdateRemark:What is modified? 
 */  
public class MyDateUtil {  
    private final static String DATE_FORMAT = "yyyy-MM-dd";  
    private final static String DATE_FORMAT_CN = "yyyy��MM��dd��";  
    private final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";  
    private final static String TIME_FORMAT_CN = "yyyy��MM��dd�� HH:mm:ss";  
    private final static String MONTH_FORMAT = "yyyy-MM";  
    private final static String DAY_FORMAT = "yyyyMMdd";  
  
    /** 
     * @Title:getMonthFirstDay 
     * @Description: �õ���ǰ�µĵ�һ��. 
     * @return 
     * @return String 
     */  
    public static String getMonthFirstDay() {  
        Calendar cal = Calendar.getInstance();  
        // ����һ,Ĭ��ֻ���õ�����·�.  
        // Calendar f = (Calendar) cal.clone();  
        // f.clear();  
        // f.set(Calendar.YEAR, cal.get(Calendar.YEAR));  
        // f.set(Calendar.MONTH, cal.get(Calendar.MONTH));  
        // f.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DATE));  
        // return DateFormatUtils.format(f, DATE_FORMAT);  
  
        // ������.  
        cal.set(Calendar.DATE, 1);  
        return DateFormatUtils.format(cal, DATE_FORMAT);  
  
    }  
  
    /** 
     * @Title:getMonthLastDay 
     * @Description: �õ���ǰ�����һ�� 
     * @return 
     * @return String 
     */  
    public static String getMonthLastDay() {  
        Calendar cal = Calendar.getInstance();  
        Calendar f = (Calendar) cal.clone();  
        f.clear();  
        // ����һ  
        // f.set(Calendar.YEAR, cal.get(Calendar.YEAR));  
        // f.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);  
        // f.set(Calendar.MILLISECOND, -1);  
        // return DateFormatUtils.format(f, DATE_FORMAT);  
  
        // ������  
        // f.set(Calendar.YEAR, cal.get(Calendar.YEAR));  
        // f.set(Calendar.MONTH, cal.get(Calendar.MONTH));  
        // f.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));  
        // return DateFormatUtils.format(f, DATE_FORMAT);  
  
        // ������(ͬһ)  
        cal.set(Calendar.DATE, 1);// ��Ϊ��ǰ�µ�1��  
        cal.add(Calendar.MONTH, 1);// ��һ���£���Ϊ���µ�1��  
        cal.add(Calendar.DATE, -1);// ��ȥһ�죬��Ϊ�������һ��  
        return DateFormatUtils.format(cal, DATE_FORMAT);  
    }  
  
    /** 
     * @Title:getPreviousMonthFirst 
     * @Description: �õ��ϸ��µĵ�һ�� 
     * @return 
     * @return String 
     */  
    public static String getPreviousMonthFirst() {  
        Calendar cal = Calendar.getInstance();  
        Calendar f = (Calendar) cal.clone();  
        f.clear();  
        // ����һ  
        // f.set(Calendar.YEAR, cal.get(Calendar.YEAR));  
        // f.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);  
        // f.set(Calendar.DATE, 1);  
        // return DateFormatUtils.format(f, DATE_FORMAT);  
  
        // ������  
        // f.set(Calendar.YEAR, cal.get(Calendar.YEAR));  
        // f.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);  
        // f.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DATE));  
        // return DateFormatUtils.format(f, DATE_FORMAT);  
  
        // ������(ͬһ)  
        cal.set(Calendar.DATE, 1);// ��Ϊ��ǰ�µ�1��  
        cal.add(Calendar.MONTH, -1);  
        return DateFormatUtils.format(cal, DATE_FORMAT);  
    }  
  
    /** 
     * @Title:getPreviousMonthEnd 
     * @Description: �õ��ϸ������һ�� 
     * @return 
     * @return String 
     */  
    public static String getPreviousMonthEnd() {  
        Calendar cal = Calendar.getInstance();  
        Calendar f = (Calendar) cal.clone();  
        f.clear();  
        // ����һ  
        // f.set(Calendar.YEAR, cal.get(Calendar.YEAR));  
        // f.set(Calendar.MONTH, cal.get(Calendar.MONTH));  
        // f.set(Calendar.MILLISECOND, -1);  
        // return DateFormatUtils.format(f, DATE_FORMAT);  
  
        // ������  
        // f.set(Calendar.YEAR, cal.get(Calendar.YEAR));  
        // f.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);  
        // f.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));  
        // return DateFormatUtils.format(f, DATE_FORMAT);  
  
        // ������(ͬһ)  
        cal.set(Calendar.DATE, 1);// ��Ϊ��ǰ�µ�1��  
        cal.add(Calendar.MONTH, 0);//  
        cal.add(Calendar.DATE, -1);// ��ȥһ�죬��Ϊ�������һ��  
        return DateFormatUtils.format(cal, DATE_FORMAT);  
    }  
  
    /** 
     * @Title:getNextMonthFirst 
     * @Description: �õ��¸��µĵ�һ�� 
     * @return 
     * @return String 
     */  
    public static String getNextMonthFirst() {  
        Calendar cal = Calendar.getInstance();  
        Calendar f = (Calendar) cal.clone();  
        f.clear();  
        // ����һ  
        // f.set(Calendar.YEAR, cal.get(Calendar.YEAR));  
        // f.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);  
        // f.set(Calendar.DATE, 1);  
        // or f.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DATE));  
        // return DateFormatUtils.format(f, DATE_FORMAT);  
  
        // ������  
        cal.set(Calendar.DATE, 1);// ��Ϊ��ǰ�µ�1��  
        cal.add(Calendar.MONTH, +1);// ��һ���£���Ϊ���µ�1��  
        return DateFormatUtils.format(cal, DATE_FORMAT);  
    }  
  
    /** 
     * @Title:getNextMonthEnd 
     * @Description: �õ��¸������һ�졣 
     * @return 
     * @return String 
     */  
    public static String getNextMonthEnd() {  
        Calendar cal = Calendar.getInstance();  
        // cal.set(Calendar.DATE, +1);  
        // cal.add(Calendar.MONTH, +2);  
        // cal.add(Calendar.DATE, -1);  
        // return DateFormatUtils.format(cal, DATE_FORMAT);  
  
        // ������  
        cal.add(Calendar.MONTH, 1);// ��һ����  
        cal.set(Calendar.DATE, 1);// ����������Ϊ���µ�һ��  
        cal.roll(Calendar.DATE, -1);// ���ڻع�һ�죬Ҳ���Ǳ������һ��  
        return DateFormatUtils.format(cal, DATE_FORMAT);  
    }  
  
    /** 
     * @Title:getCurrentMonthDays 
     * @Description: �õ���ǰ�µ����� 
     * @return 
     * @return int 
     */  
    public static int getCurrentMonthDays() {  
        Calendar cal = new GregorianCalendar();// Calendar.getInstance();  
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
        return days;  
    }  
  
    /** 
     * @Title:getSpecifiedMonthDays 
     * @Description: �õ�ָ�����·ݵ����� 
     * @param date 
     * @return 
     * @return int 
     */  
    public static int getSpecifiedMonthDays(String date) {  
        Calendar cal = Calendar.getInstance();  
        try {  
            cal.setTime(DateUtils.parseDate(date, MONTH_FORMAT));  
            int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
            return days;  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        }  
        return 0;  
    }  
  
    /** 
     * @Title:getCurrentDate 
     * @Description: �õ���ǰ���� 
     * @return 
     * @return String 
     */  
    public static String getCurrentDate() {  
        Calendar cal = Calendar.getInstance();  
        String currentDate = DateFormatUtils.format(cal, DATE_FORMAT);  
        return currentDate;  
    }  
  
    /** 
     * @Title:getCurrentTime 
     * @Description: �õ���ǰ��ʱ�� 
     * @return 
     * @return String 
     */  
    public static String getCurrentTime() {  
        Calendar cal = Calendar.getInstance();  
        String currentDate = DateFormatUtils.format(cal, TIME_FORMAT);  
        return currentDate;  
    }  
  
    /** 
     * @Title:getOffsetDate 
     * @Description: �õ��뵱ǰ����ƫ����ΪX�����ڡ� 
     * @param offset 
     * @return 
     * @return String 
     */  
    public static String getOffsetDate(int offset) {  
        Calendar cal = Calendar.getInstance();  
        cal.add(Calendar.DAY_OF_MONTH, offset);  
        String currentDate = DateFormatUtils.format(cal, DATE_FORMAT);  
        return currentDate;  
    }  
  
    /** 
     * @Title:getSpecifiedOffsetDate 
     * @Description: �õ���ָ������ƫ����ΪX�����ڡ� 
     * @param specifiedDateָ�������� 
     *            ,��ʽΪYYYY-MM-DD 
     * @param offset 
     * @return ����yyyy-MM-dd��ʽ���ַ������� 
     * @return String 
     * @throws ParseException 
     */  
    public static String getSpecifiedOffsetDate(String specifiedDate, int offset) throws ParseException {  
        Date date = DateUtils.parseDate(specifiedDate, DATE_FORMAT);  
        Calendar cal = DateUtils.toCalendar(date);  
        cal.add(Calendar.DAY_OF_MONTH, offset);  
        String returnDate = DateFormatUtils.format(cal, DATE_FORMAT);  
        return returnDate;  
    }  
  
    /** 
     * @Title:getSpecifiedOffsetTime 
     * @Description: �õ���ָ������ʱ��ƫ����ΪX��ʱ�䡣 
     * @param specifiedTime 
     *            ָ����ʱ��,��ʽΪyyyy-MM-dd HH:mm:ss 
     * @param offset 
     *            ƫ������ 
     * @return ����yyyy-MM-dd HH:mm:ss��ʽ���ַ���ʱ�� 
     * @throws ParseException 
     * @return String 
     */  
    public static String getSpecifiedOffsetTime(String specifiedTime, int offset) throws ParseException {  
        Date date = DateUtils.parseDate(specifiedTime, TIME_FORMAT);  
        Calendar cal = DateUtils.toCalendar(date);  
        cal.add(Calendar.DAY_OF_MONTH, offset);  
        String returnDate = DateFormatUtils.format(cal, TIME_FORMAT);  
        return returnDate;  
    }  
  
    /** 
     * @Title:getOffsetDateTime 
     * @Description: �õ���ָ������ʱ��ƫ����ΪX��ʱ�䡣 
     * @param specifiedDateTime 
     *            ָ����ʱ��,��ʽΪyyyy-MM-dd HH:mm:ss/yyyy-MM-dd 
     * @param offset 
     *            ƫ������ 
     * @return 
     * @throws ParseException 
     * @return String 
     */  
    public static String getOffsetDateTime(String specifiedDateTime, int offset) throws ParseException {  
        String regexStr = "\\d{4}-\\d{2}-\\d{2}";  
        if (specifiedDateTime.matches(regexStr)) {  
            return getSpecifiedOffsetDate(specifiedDateTime, offset);  
        } else {  
            return getSpecifiedOffsetTime(specifiedDateTime, offset);  
        }  
    }  
  
    /** 
     * �ж��Ƿ�Ϊ���� 
     *  
     * @param year 
     * @return 
     */  
    public static boolean isLeapYear(int year) {  
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);  
    }  
  
    /** 
     * @Title:getWeekDay 
     * @Description: �ж������ڼ�. 
     * @param c 
     * @return 
     * @return String 
     */  
    public static String getWeekDay(Calendar c) {  
        if (c == null) {  
            return "����һ";  
        }  
        switch (c.get(Calendar.DAY_OF_WEEK)) {  
        case Calendar.MONDAY:  
            return "����һ";  
        case Calendar.TUESDAY:  
            return "���ڶ�";  
        case Calendar.WEDNESDAY:  
            return "������";  
        case Calendar.THURSDAY:  
            return "������";  
        case Calendar.FRIDAY:  
            return "������";  
        case Calendar.SATURDAY:  
            return "������";  
        default:  
            return "������";  
        }  
    }  
  
    /** 
     * @Title:getDaysListBetweenDates 
     * @Description: �����������֮�����������. 
     * @param begin 
     *            ��ʼ���� . 
     * @param end 
     *            �������� . 
     * @return 
     * @return List<String> 
     */  
    public static List<String> getDaysListBetweenDates(String begin, String end) {  
        List<String> dateList = new ArrayList<String>();  
        Date d1;  
        Date d2;  
        try {  
            d1 = DateUtils.parseDate(begin, DATE_FORMAT);  
            d2 = DateUtils.parseDate(end, DATE_FORMAT);  
            if (d1.compareTo(d2) > 0) {  
                return dateList;  
            }  
            do {  
                dateList.add(DateFormatUtils.format(d1, DATE_FORMAT));  
                d1 = DateUtils.addDays(d1, 1);  
            } while (d1.compareTo(d2) <= 0);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return dateList;  
    }  
  
    /** 
     * @Title:getMonthsListBetweenDates 
     * @Description: ����������·� 
     * @param begin 
     * @param end 
     * @return 
     * @return List<String> 
     */  
    public static List<String> getMonthsListBetweenDates(String begin, String end) {  
        List<String> dateList = new ArrayList<String>();  
        Date d1;  
        Date d2;  
        try {  
            d1 = DateUtils.parseDate(begin, DATE_FORMAT);  
            d2 = DateUtils.parseDate(end, DATE_FORMAT);  
            if (d1.compareTo(d2) > 0) {  
                return dateList;  
            }  
            do {  
                dateList.add(DateFormatUtils.format(d1, MONTH_FORMAT));  
                d1 = DateUtils.addMonths(d1, 1);  
            } while (d1.compareTo(d2) <= 0);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return dateList;  
    }  
  
    /** 
     * @Title:long2Time 
     * @Description: ��long���͵�ʱ��ֵת���ɱ�׼��ʽ��ʱ�䣨yyyy-MM-dd HH:mm:ss�� 
     * @param createTime 
     * @return 
     * @return String 
     */  
    public static String long2Time(String createTime) {  
        long fooTime = Long.parseLong(createTime) * 1000L;  
        return DateFormatUtils.format(fooTime, TIME_FORMAT);  
    }  
  
    public static void main(String[] args) throws ParseException {  
        System.out.println(getMonthFirstDay());  
        System.out.println(getMonthLastDay());  
        System.out.println(getPreviousMonthFirst());  
        System.out.println(getPreviousMonthEnd());  
        System.out.println(getNextMonthFirst());  
        System.out.println(getNextMonthEnd());  
        System.out.println(getCurrentMonthDays());  
        System.out.println(getSpecifiedMonthDays("1900-02"));  
        System.out.println(getCurrentDate());  
        System.out.println(getOffsetDate(-4));  
        System.out.println(isLeapYear(1900));  
        System.out.println(getWeekDay(Calendar.getInstance()));  
        System.out.println(getDaysListBetweenDates("2012-1-12", "2012-1-21"));  
        System.out.println(getMonthsListBetweenDates("2012-1-12", "2012-3-21"));  
        System.out.println(getSpecifiedOffsetTime("2012-09-09 12:12:12", 12));  
        System.out.println(getOffsetDateTime("2012-09-09", 12));  
        System.out.println(getOffsetDateTime("2012-09-09 12:12:12", 12));  
        System.out.println(long2Time("1234567890"));  
  
    }  
}  