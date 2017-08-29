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
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
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
     * getLength �����ַ����ĳ���
     * 
     * @param src �����ַ���
     * @return int �ַ�������
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
     * getLength ���طǿ��ַ���
     * 
     * @param o �������
     * @return string
     * 
     */
    public static String nvl(Object o)
    {
        return (null == o) ? "" : o.toString().trim();
    }

    /**
     * replace$ �����ַ�������һ��$����Ϊ����$
     * 
     * @param instr �����ַ���
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
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static String getMsg(String value, Object[] params)
    {
        try
        {
            // ��ʽ������
            return MessageFormat.format(value, params);
        }
        catch (Exception ex)
        {
            return value;
        }
    }

    /**
     * ��
     * 
     * @param s
     * @param def
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * Ԫת��
     * 
     * @param s
     * @param def
     * @return [����˵��]
     * 
     * @return float [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * @return [����˵��]
     * 
     * @return float [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * String to int <������ϸ����>
     * 
     * @param s
     * @param def
     * @return [����˵��]
     * 
     * @return int [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * String to Long <������ϸ����>
     * 
     * @param s
     * @param def
     * @return [����˵��]
     * 
     * @return int [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * Ҷ����ʾʱ�������ַ�����ת��
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
     * �����ַ�ת�壬�ı�תΪhtml
     * 
     * @param text
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * Ϊwml��wap1.0���������ַ�����ת��
     * 
     * @param text
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static String escForWml(String text)
    {
        text = replace(text, "$", "$$");
        return text;
    }

    /**
     * �����滻�ַ����е��Ӵ���
     * 
     * @param text �������ַ�����
     * @param find ���滻���Ӵ���
     * @param replace �滻�ɵ��Ӵ���
     * @return ����text�滻��Ľ����
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
        int begin = 0; // �´μ�����ʼ��λ��
        int i = text.indexOf(find); // �ҵ����Ӵ�λ��
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
     * ȥ����λ�Ĵ������ֵ�ĩλ0
     * 
     * @param discount
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * �ж��ַ����Ƿ�Ϊnull������ǿհ��ַ�
     */
    public static boolean isEmpty(String str)
    {
        return ((str == null) || (str.trim().length() == 0));
    }

    /**
     * �ж��ַ����Ƿ�buΪnull������ǿհ��ַ�
     */
    public static boolean isNotEmpty(String str)
    {
        return !isEmpty(str);
    }

    /**
     * ���ַ���ͨ�� �ָ���ת��Ϊ����
     * 
     * @param collection ��Ҫ��ŵļ���
     * @param str �ַ���
     * @param splitStr �ָ���
     * 
     * @return Collection [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * �ж��ַ����Ƿ�������
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
     * �滻��б��"\"Ϊб��"/"
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
     * ȡ�Ӵ�
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
     * �ж��ַ����Ƿ��������ĳ���������
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
     * �ж��ַ����Ƿ��������е�ĳ���������
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
     * ɾ��ĩβ�Ŀո񣬲���ȫ�ǿո��滻Ϊ&nbsp <������ϸ����>
     * 
     * @param str
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     */
    public static String replaceFullSpaceToNbsp(String str)
    {
        // ���strȫ���ɿո�(����ȫ�ǿո�)���
        if (isEmpty(str) || isEmpty(str.replace("��", "").trim()))
        {
            return "";
        }

        str = str.trim();
        // ɾ��ĩβ��ȫ�ǿո�,\\u3000��ȫ�ǿո��ASC��
        str = str.replaceAll("[\u3000]+$", "");

        return str.replace("\u3000", "&nbsp;&nbsp;");
    }

    /**
     * <һ�仰���ܼ���> ��ȡ�û��Զ���ê������ <������ϸ����>
     * 
     * @param rule
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * ��null������ǿհ��ַ�������Ĭ��ֵ��������trim���
     * 
     * @param str
     * @return [����˵��]
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
     * ��ȡ�ַ���(��ȡ���������ʱ��ȡȫ)
     * 
     * @param str
     * @return [����˵��]
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
     * �ַ������ֽڽ�ȡ����ȡ���������ʱ�����������ģ�
     * 
     * ��ԭ�ַ����Ƚ�ȡ�ĳ���Ҫ�̻����ʱ������ԭ�ַ��� ����Ҫ��ȡ�ַ���ʱ�����ʡ�Է��ų��ȴ���Ҫ��ȡ�ĳ���ʱ�������ؿ�ֵ �����ȡ�������Ϊ�գ���ôҲ���ؿ�ֵ����Я��ʡ�Ժ�
     * �����ȡ���Ǻ��֣����ֻ�ܽ�ȡ��������֣��򽫲���ȡ�ú����ַ�
     * 
     * @param str Ҫ��ȡ���ַ���
     * @param len ��ȡ�ĳ���
     * @param elide ʡ�Ժ�
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
        { // ����Ҫ�õ�ʡ�Ժ�
            return str;
        }

        // ��Ҫ�õ�ʡ�Ժ�
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

        // ��ȡʱ���漰���ĺ�����Ŀ(һ������ռ2��)
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
     * ��ʱ���ʽ�ַ���date�е�ʱ���滻des�е�yyyy MM dd��������ڵĻ���
     * 
     * @param str
     * @return [����˵��]
     */
    public static String replaceStringWithDate(String des, String date)
    {
        // modified by tianli for IRD-21827 begin:���ڸ�ʽСʱӦ��ΪHH������hh
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
     * <һ�仰���ܼ���> <������ϸ����>
     * 
     * @param target
     * @param partStr
     * @param replaceMent [����˵��]
     * 
     * @return void [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * ɾ��С��������0,���磺5.40 -> 5.4 ; 5.00->5 ; 5.04 ����
     */
    public static String removeZeroAfterDecimalPoint(String data)
    {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(10);
        return nf.format(Double.valueOf(data));
    }

    /** added by luojianjun lKF66515 at 2012-6-29 for REQ-1333 begin */
    /**
     * ���urlǰ����http://����� <������ϸ����>
     * 
     * @param url
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     ** <һ�仰���ܼ���>��Ĭ���ַ������������ַ���<������ϸ����>
     * 
     * @param tempStr Ŀ���ַ���
     * @param def Ĭ���ַ���
     * @return [����˵��]
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
     * ��ȡ������server��ַ <������ϸ����>
     * 
     * @param url
     * @return
     * @see [�ࡢ��#��������#��Ա]
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
     * �ж��ַ����Ƿ��Ա����Ž�β����š����š��ֺš�̾�š��ٺţ�
     */
    public static boolean endWithPunctuation(String str)
    {
        if (isEmpty(str))
        {
            return false;
        }

        return str.endsWith("��") || str.endsWith(".") || str.endsWith("��") || str.endsWith(",") || str.endsWith("��")
            || str.endsWith(";") || str.endsWith("!") || str.endsWith("��") || str.endsWith("��");
    }

    /**
     * �ַ���Ϊ��ʹ��Ĭ��ֵ
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
     * �������ַ�������URL����
     * 
     * @param input
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * �������ַ�������URL����
     * 
     * @param input
     * @return [����˵��]
     * 
     * @return String [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
     * ����null�����trim����
     * 
     * @param str
     * @return [����˵��]
     */
    public static String trim(String str)
    {
        return str == null ? null : str.trim();
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
     * �ж������ַ����Ƿ����
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
     * �ж������ַ����Ƿ����
     */
    public static boolean isNotEq(String str, String other)
    {
        return !isEq(str, other);
    }

    /**
     * �ж��ַ����������Ƿ����ַ��������
     */
    public static boolean isEq(String str, int other)
    {
        return String.valueOf(other).equals(str);
    }

    /**
     * �ж��ַ����������Ƿ����ַ����ϲ����
     */
    public static boolean isNotEq(String str, int other)
    {
        return !isEq(str, other);
    }

    /**
     * �ж��ַ����������Ƿ����ַ��������
     */
    public static boolean isEq(int i, String str)
    {
        return String.valueOf(i).equals(str);
    }

    /**
     * �ж��ַ����������Ƿ����ַ����ϲ����
     */
    public static boolean isNotEq(int i, String str)
    {
        return !isEq(i, str);
    }

    /**
     * �жϸ��ַ����Ƿ������ĳ�����Ͳ������ַ��������
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
     * �����ַ����ֽ�����ȡ�ַ���
     * 
     * @param str
     * @param len ��Ҫ��ȡ���ֽ������˴�һ�����İ�ռ��2���ֽ�
     * @return
     * @see [�ࡢ��#��������#��Ա]
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
     * �ж��ַ����Ƿ���11λ�ֻ�������
     */
    public static boolean isMsisdn(String str)
    {
        return isDigtial(str) && str.length() == 11;
    }
    
    /**
     * ��֤����������ʽ�Ƿ����
     * 
     * @param email
     * @return �Ƿ�Ϸ�
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
     * @param length ����
     * @param type ���� 1:���� 2:��ĸ 3:���ֺ���ĸ���
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
     * ����ȡ5λ����� <������ϸ����>
     * 
     * @return [����˵��]
     * 
     * @return int [��������˵��]
     * @exception throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
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
