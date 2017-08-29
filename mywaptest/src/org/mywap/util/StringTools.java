package org.mywap.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;


/**
 * 
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class StringTools
{

    public static final String STRING_TYPE_CHAR = "1";
    public static final String STRING_TYPE_NUMBER = "2";
    
    public static final String DEFAULT_CHARSET = "UTF-8";
    
    private static Logger logger = Logger.getLogger(StringTools.class);

    private StringTools()
    {
    }

    /**
     * getLength 返回字符串的长度
     * 
     * @param src 输入字符串
     * @return int 字符串长度
     * 
     */

    public static int getLength(String src)
    {
        try
        {
            return ((null == src) || ("".equals(src))) ? 0 : src.getBytes(DEFAULT_CHARSET).length;
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error("", e);
            return 0;
        }
    }

    /**
     * getLength 返回非空字符串
     * 
     * @param o 输入对象
     * @return string
     * 
     */
    public static String nvl(Object o)
    {
        return (null == o) ? "" : o.toString().trim();
    }

    /**
     * replace$ 返回字符串，将一个$更改为两个$
     * 
     * @param instr 输入字符串
     * @return String
     */
    public static String replace$(String instr)
    {
        StringBuffer sb = new StringBuffer(instr);
        int place = sb.indexOf("$");
        while (place >= 0)
        {
            sb.replace(place, place + 1, "$$");
            place = sb.indexOf("$", place + 2);
        }
        return sb.toString();
    }

    /**
     * 
     * @param value
     * @param params
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getMsg(String value, Object[] params)
    {
        try
        {
            // 格式化数据
            return MessageFormat.format(value, params);
        }
        catch (Exception ex)
        {
            return value;
        }
    }

    /**
     * 分
     * 
     * @param s
     * @param def
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String fenToYuan(String s, float def)
    {
        float fen = StringTools.toFloat(s, 0.0f);
        String sFen = (int) Math.floor(fen) + "";
        int len = sFen.length();

        String yuan;
        if (len == 1)
        {
            yuan = "0.0" + sFen;
        }
        else if (len == 2)
        {
            yuan = "0." + sFen;
        }
        else
        {
            yuan = sFen.substring(0, len - 2) + '.' + sFen.substring(len - 2);
        }

        return (fen < 0) ? ('-' + yuan) : yuan;
    }

    /**
     * 元转分
     * 
     * @param s
     * @param def
     * @return [参数说明]
     * 
     * @return float [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static int yuanToFen(String s, int def)
    {
        int fen;
        try
        {
            BigDecimal sumStr = new BigDecimal(s);
            fen = sumStr.movePointRight(2).intValue();
        }
        catch (Exception e)
        {
            fen = (int) (def * 100);
        }

        return fen;
    }

    /**
     * String to float
     * 
     * @param s
     * @param def
     * @return [参数说明]
     * 
     * @return float [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static float toFloat(String s, float def)
    {
        float f = def;

        try
        {
            f = Float.parseFloat(s);
        }
        catch (Exception e)
        {
            f = def;
        }
        return f;
    }

    /**
     * String to int <功能详细描述>
     * 
     * @param s
     * @param def
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static int toInt(String s, int def)
    {
        int value = def;

        try
        {
            value = Integer.parseInt(s);
        }
        catch (Exception e)
        {
            value = def;
        }

        return value;
    }

    /**
     * String to Long <功能详细描述>
     * 
     * @param s
     * @param def
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Long toLong(String s, long def)
    {
        long value = def;

        try
        {
            value = Long.parseLong(s);
        }
        catch (Exception e)
        {
            value = def;
        }

        return value;
    }

    /**
     * 叶面显示时对特殊字符进行转换
     * 
     * @param String
     * @return string
     */
    public static String toView(String text)
    {
        text = replace(text, "&amp;", "&");
        text = replace(text, "&lt;", "<");
        text = replace(text, "&gt;", ">");
        text = replace(text, "&quot;", "\"");
        text = replace(text, "&apos;", "'");
        return text;
    }

    /**
     * 特殊字符转义，文本转为html
     * 
     * @param text
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String text2Html(String text)
    {
        text = replace(text, "&", "&amp;");
        text = replace(text, "<", "&lt;");
        text = replace(text, ">", "&gt;");
        text = replace(text, "\"", "&quot;");
        text = replace(text, "'", "&apos;");
        return text;
    }

    /**
     * 为wml（wap1.0）的特殊字符进行转义
     * 
     * @param text
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String escForWml(String text)
    {
        text = replace(text, "$", "$$");
        return text;
    }

    /**
     * 查找替换字符串中的子串。
     * 
     * @param text 待处理字符串。
     * @param find 待替换的子串。
     * @param replace 替换成的子串。
     * @return 返回text替换后的结果。
     */
    public static String replace(String text, String find, String replace)
    {
        if (text == null || find == null || replace == null)
        {
            return text;
        }
        int findLen = find.length();
        int textLen = text.length();
        if (textLen == 0 || findLen == 0)
        {
            return text;
        }
        StringBuffer sb = new StringBuffer(256);
        int begin = 0; // 下次检索开始的位置
        int i = text.indexOf(find); // 找到的子串位置
        while (i != -1)
        {
            sb.append(text.substring(begin, i));
            sb.append(replace);
            begin = i + findLen;
            i = text.indexOf(find, begin);
        }
        if (begin < textLen)
        {
            sb.append(text.substring(begin));
        }
        return sb.toString();
    }

    /**
     * 去掉两位的打折数字的末位0
     * 
     * @param discount
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String convertDiscount(String discount)
    {
        if (discount == null || "".equals(discount.trim()))
        {
            return "";
        }
        else if (discount.endsWith("0"))
        {
            return discount.substring(0, discount.length() - 1);
        }
        else
        {
            return discount;
        }
    }

    /**
     * 判断字符串是否为null对象或是空白字符
     */
    public static boolean isEmpty(String str)
    {
        return ((str == null) || (str.trim().length() == 0));
    }

    /**
     * 判断字符串是否bu为null对象或是空白字符
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * 将字符串通过 分隔符转换为集合
     * 
     * @param collection 需要存放的集合
     * @param str 字符串
     * @param splitStr 分隔符
     * 
     * @return Collection [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static Collection<String> toCollection(Collection<String> collection, String str, String splitStr)
    {
        if (null != str && !"".equals(str) && null != splitStr && !"".equals(splitStr))
        {
            String[] strArray = str.trim().split(splitStr);
            for (String tmpStr : strArray)
            {
                collection.add(tmpStr.trim());
            }
        }

        return collection;
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isDigtial(String str)
    {
        if (isEmpty(str))
        {
            return false;
        }
        return str.matches("\\d+");
    }

    /**
     * 替换反斜线"\"为斜线"/"
     */
    public static String replaceBacklash(String str)
    {
        if (isEmpty(str))
        {
            return "";
        }
        else
        {
            return str.replace("\\", "/");
        }
    }

    /**
     * 取子串
     */
    public static String subString(String str, int begin, int end)
    {
        if (str == null)
        {
            return str;
        }

        int b = Math.max(begin, 0);
        int e = Math.min(end, str.length());
        return str.substring(b, e);
    }

    /**
     * 判断字符串是否与其后面某个参数相等
     */
    public static boolean matchs(String base, String... matched)
    {
        for (int i = 0; i < matched.length; i++)
        {
            if (matched[i].equals(base))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否与数组中的某个参数相等
     */
    public static boolean matches(String base, String[] matches)
    {
        if (null == matches || 0 == matches.length)
        {
            return false;
        }
        else
        {
            for (int i = 0; i < matches.length; i++)
            {
                if (matches[i].equals(base))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 删除末尾的空格，并将全角空格替换为&nbsp <功能详细描述>
     * 
     * @param str
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String replaceFullSpaceToNbsp(String str)
    {
        // 如果str全部由空格(包括全角空格)组成
        if (isEmpty(str) || isEmpty(str.replace("　", "").trim()))
        {
            return "";
        }

        str = str.trim();
        // 删除末尾的全角空格,\\u3000是全角空格的ASC码
        str = str.replaceAll("[\u3000]+$", "");

        return str.replace("\u3000", "&nbsp;&nbsp;");
    }

    /**
     * <一句话功能简述> 获取用户自定义锚文描述 <功能详细描述>
     * 
     * @param rule
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getUserDefinedName(String rule)
    {
        if (rule.indexOf("/") < 0)
        {
            return "";
        }
        String tempString = rule.split("/")[1];
        return StringTools.text2Html(tempString.substring(0, tempString.length() - 1));
    }

    /**
     * 若null对象或是空白字符，返回默认值，否则其trim结果
     * 
     * @param str
     * @return [参数说明]
     */
    public static String getTrim(String str, String def)
    {
        if (str == null)
        {
            return def;
        }

        String t = str.trim();
        return (t.length() == 0) ? def : t;
    }

    /**
     * 截取字符串(获取到半个中文时，取全)
     * 
     * @param str
     * @return [参数说明]
     */
    public static String getStrBylength(String str, int len, String des)
    {
        if (null == str)
            return "";
        int strByteLen = 0;
        try
        {
            strByteLen = str.getBytes(DEFAULT_CHARSET).length;
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error("", e);
        }
        int strLen = str.length();
        if (len >= strByteLen)
            return str;
        if (strByteLen > strLen)
        {
            StringBuffer sb = new StringBuffer();
            char[] charSet = str.toCharArray();
            for (int i = 0, j = 0; i < strLen && j < len; i++)
            {
                if (String.valueOf(charSet[i]).matches("[^x00-xff]"))
                {
                    sb.append(charSet[i]);
                    j += 2;
                }
                else
                {
                    sb.append(charSet[i]);
                    j += 1;
                }
            }
            return sb.toString() + des;
        }
        else
        {
            return str.substring(0, len) + des;
        }
    }

    /**
     * 字符串按字节截取（获取到半个中文时，抛弃该中文）
     * 
     * 当原字符串比截取的长度要短或相等时，返回原字符串 当需要截取字符串时，如果省略符号长度大于要截取的长度时，将返回空值 如果截取后的内容为空，那么也返回空值，不携带省略号
     * 如果截取的是汉字，如果只能截取到半个汉字，则将不获取该汉字字符
     * 
     * @param str 要截取的字符串
     * @param len 截取的长度
     * @param elide 省略号
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getStrByLengthForShort(String str, int len, String elide)
    {
        // modify by hKF40364 at Sep 9, 2011 for IRD-19391 begin

        if (StringTools.isEmpty(str))
        {
            return "";
        }

        byte[] strByte = null;
        try
        {
            strByte = str.getBytes(DEFAULT_CHARSET);
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error("", e);
        }
        int strLen = strByte.length;

        if (len >= strLen)
        { // 不需要用到省略号
            return str;
        }

        // 需要用到省略号
        elide = elide == null ? "" : elide;
        int elideLen = 0;
        try
        {
            elideLen = elide.getBytes(DEFAULT_CHARSET).length;
        }
        catch (UnsupportedEncodingException e1)
        {
            logger.error("", e1);
        }

        if (len <= elideLen)
        {
            return "";
        }
        len = len - elideLen;

        // 截取时，涉及到的汉字数目(一个汉字占2个)
        int count = 0;
        for (int i = 0; i < len; i++)
        {
            int value = (int) strByte[i];
            if (value < 0)
            {
                count++;
            }
        }
        if (count % 2 != 0)
        {
            len = len - 1;
        }
        String subString = null;
        try
        {
            subString = new String(strByte, 0, len, DEFAULT_CHARSET);
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error("", e);
        }

        subString = StringTools.isEmpty(subString) ? "" : subString + elide;

        return subString;

        // modify by hKF40364 at Sep 9, 2011 for IRD-19391 end

    }

    // add by tianli 00165293 for IRD-21626 begin
    /**
     * 用时间格式字符串date中的时间替换des中的yyyy MM dd（如果存在的话）
     * 
     * @param str
     * @return [参数说明]
     */
    public static String replaceStringWithDate(String des, String date)
    {
        // modified by tianli for IRD-21827 begin:日期格式小时应该为HH而不是hh
        String temp = des;
        if (-1 != temp.indexOf("yy") && -1 == temp.indexOf("yyyy"))
        {
            temp = temp.replace("yy", StringTools.nvl(DateTools.timeTransform(date, DateTools.DATE_FORMAT_14, "yy")));
        }
        if (-1 != temp.indexOf("yyyy"))
        {
            temp = temp.replace("yyyy",
                StringTools.nvl(DateTools.timeTransform(date, DateTools.DATE_FORMAT_14, "yyyy")));
        }
        if (-1 != temp.indexOf("MM"))
        {
            temp = temp.replace("MM", StringTools.nvl(DateTools.timeTransform(date, DateTools.DATE_FORMAT_14, "MM")));
        }
        if (-1 != temp.indexOf("dd"))
        {
            temp = temp.replace("dd", StringTools.nvl(DateTools.timeTransform(date, DateTools.DATE_FORMAT_14, "dd")));
        }
        return temp;
    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param target
     * @param partStr
     * @param replaceMent [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String modifyDesc(String target, String partStr, String replaceMent)
    {
        if (StringTools.isEmpty(target) || StringTools.isEmpty(partStr) || !target.contains(partStr))
        {
            return target;
        }
        return target.trim().replace(partStr, replaceMent);
    }

    /** added by zhaoxinwei zKF40547 at 2012-5-9 for REQ-1116 end */

    /**
     * 删除小数点后面的0,例如：5.40 -> 5.4 ; 5.00->5 ; 5.04 不变
     */
    public static String removeZeroAfterDecimalPoint(String data)
    {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(10);
        return nf.format(Double.valueOf(data));
    }

    /** added by luojianjun lKF66515 at 2012-6-29 for REQ-1333 begin */
    /**
     * 如果url前不带http://则加上 <功能详细描述>
     * 
     * @param url
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getHttpUrl(String url)
    {
        if (isEmpty(url))
        {
            return "";
        }

        if (!url.startsWith("http://"))
        {
            StringBuffer bf = new StringBuffer();
            bf = bf.append("http://").append(url);

            return bf.toString();
        }

        return url;
    }

    /**
     ** <一句话功能简述>以默认字符串代替已有字符串<功能详细描述>
     * 
     * @param tempStr 目标字符串
     * @param def 默认字符串
     * @return [参数说明]
     */
    public static String nvl2(String tempStr, String def)
    {
        if (StringTools.isEmpty(tempStr))
        {
            return def == null ? "" : def.trim();
        }
        else
        {
            return tempStr.trim();
        }
    }

    // added by hejiahuan hKF74045 at Jun 27, 2012 for REQ-555 end

    /**
     * 截取请求中server地址 <功能详细描述>
     * 
     * @param url
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String cutServerIp(String url)
    {
        String afterUrl = url.substring(url.indexOf("://") + 3, url.length());

        if (afterUrl.lastIndexOf(":") == -1)
        {
            int charAt = afterUrl.indexOf("/");
            url = afterUrl.substring(0, charAt);
        }
        else
        {
            url = url.substring(url.indexOf("://") + 3, url.lastIndexOf(":"));
        }

        return url;
    }

    /**
     * 判断字符串是否以标点符号结尾（句号、逗号、分号、叹号、顿号）
     */
    public static boolean endWithPunctuation(String str)
    {
        if (isEmpty(str))
        {
            return false;
        }

        return str.endsWith("。") || str.endsWith(".") || str.endsWith("，") || str.endsWith(",") || str.endsWith("；")
            || str.endsWith(";") || str.endsWith("!") || str.endsWith("！") || str.endsWith("、");
    }

    /**
     * 字符串为空使用默认值
     */
    public static String asDefault(String oldStr, String def)
    {
        if (isEmpty(oldStr))
        {
            return def;
        }
        return oldStr;
    }

    /**
     * 对输入字符串进行URL编码
     * 
     * @param input
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String encode(String input)
    {
        String result = "";
        try
        {
            result = URLEncoder.encode(input, "UTF-8");
        }
        catch (Exception e)
        {
            result = input;
        }

        return result;
    }

    /**
     * 对输入字符串进行URL解码
     * 
     * @param input
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String decode(String input)
    {
        String result = "";
        try
        {
            result = URLDecoder.decode(input, "UTF-8");
        }
        catch (Exception e)
        {
            result = input;
        }

        return result;
    }

    /**
     * 允许null对象的trim方法
     * 
     * @param str
     * @return [参数说明]
     */
    public static String trim(String str)
    {
        return str == null ? null : str.trim();
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
     * 判断两个字符串是否相等
     */
    public static boolean isEq(String str, String other)
    {
        if (str == null)
        {
            return other == null;
        }
        return str.equals(other);
    }

    /**
     * 判断两个字符串是否不相等
     */
    public static boolean isNotEq(String str, String other)
    {
        return !isEq(str, other);
    }

    /**
     * 判断字符串和整数是否在字符串上相等
     */
    public static boolean isEq(String str, int other)
    {
        return String.valueOf(other).equals(str);
    }

    /**
     * 判断字符串和整数是否在字符串上不相等
     */
    public static boolean isNotEq(String str, int other)
    {
        return !isEq(str, other);
    }

    /**
     * 判断字符串和整数是否在字符串上相等
     */
    public static boolean isEq(int i, String str)
    {
        return String.valueOf(i).equals(str);
    }

    /**
     * 判断字符串和整数是否在字符串上不相等
     */
    public static boolean isNotEq(int i, String str)
    {
        return !isEq(i, str);
    }

    /**
     * 判断该字符串是否与后面某个整型参数在字符串上相等
     */
    public static boolean matchs(String base, int... matched)
    {
        int b;
        try
        {
            b = Integer.parseInt(base);
        }
        catch (Exception e)
        {
            return false;
        }

        for (int i = 0; i < matched.length; i++)
        {
            if (b == matched[i])
            {
                return true;
            }
        }
        return false;
    }

    public static boolean isEqIgnoreCase(String str, String other)
    {
        if (str == null)
        {
            return other == null;
        }

        return str.equalsIgnoreCase(other);
    }

    public static boolean isNotEqIgnoreCase(String str, String other)
    {
        return !isEqIgnoreCase(str, other);
    }
    
    public static String[] Split(String string, String type)
    {
        if ("".equals(nvl(type)))
        {
            type = ",";
        }
        if ("".equals(nvl(string)))
        {
            return null;
        }
        else
        {
            return string.split(type);
        }
    }
    
    /**
     * 
     * 根据字符串字节数截取字符串
     * 
     * @param str
     * @param len 需要截取的字节数，此处一个中文按占用2个字节
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getStringByByte(String str, int len)
    {
        if(isEmpty(str))
        {
            return str;
        }
        int count = 0;
        char[] chs = str.toCharArray();
        for(int i = 0; i < chs.length; i++) 
        {
            if(count < len)
            {
                count += (chs[i] > 0xff) ? 2 : 1;
                if(count > len)
                {
                    return new StringBuffer(str).substring(0,i)+"...";
                }
                else if(i == (chs.length-1)&& count == len)
                {
                    return new StringBuffer(str).substring(0,i+1);
                } 
                else if(count == len)
                {
                    return new StringBuffer(str).substring(0,i+1)+"...";
                }
            }
        }
        
        return str;
    }
    
    
    /**
     * 判断字符串是否是11位手机号数字
     */
    public static boolean isMsisdn(String str)
    {
        return isDigtial(str) && str.length() == 11;
    }
    
    /**
     * 验证输入的邮箱格式是否符合
     * 
     * @param email
     * @return 是否合法
     */  
    public static boolean isEmail(String email){  
        boolean tag = true;  
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
        final Pattern pattern = Pattern.compile(pattern1);  
        final Matcher mat = pattern.matcher(email);  
        if (!mat.find()) {  
            tag = false;  
        }  
        return tag;  
    }  
      
    /**
     * 
     * @param length 长度
     * @param type 类型 1:数字 2:字母 3:数字和字母组合
     * @return
     */
    public static String getCharAndNumr(int length, Object ... type)     
	{     
	    String val = "";     
	    Random random = new Random();  
	    String charOrNum;
	    for(int i = 0; i < length; i++)     
	    {     
	    	if(type.length >=1 && STRING_TYPE_CHAR.equals(type[0])){
	    		charOrNum = "char";
	    	}else if(type.length >=1 && STRING_TYPE_NUMBER.equals(type[0])){
	    		charOrNum = "num";
	    	}else{
	    		charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";    
	    	}
	                 
	        if("char".equalsIgnoreCase(charOrNum))
	        {     
	            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;  
	            val += (char) (choice + random.nextInt(26));     
	        }     
	        else if("num".equalsIgnoreCase(charOrNum))
	        {     
	            val += String.valueOf(random.nextInt(10));     
	        }     
	    }     
	    return val; 
	}   
    
    /**
     * 　获取5位随机数 <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static int getFiveRandomNumerical()
    {
        int n = 0;
        Random rnd = new Random();
        n = rnd.nextInt(100000);
        // n = (int)(Math.random() * 100000);
        
        while (n < 10000 || !handle(n))
        {
            n = rnd.nextInt(100000);
        }
        
        return n;
    }
    
    public static boolean handle(int n)
    {
        int[] list = new int[5];
        for (int i = 0; i < 5; i++)
        {
            list[i] = n % 10;
            n = n / 10;
        }
        for (int i = 0; i < 5; i++)
        {
            for (int j = i + 1; j < 5; j++)
            {
                if (list[i] == list[j])
                    return false;
            }
        }
        return true;
    }
    
}
