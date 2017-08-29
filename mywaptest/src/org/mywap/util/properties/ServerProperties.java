package org.mywap.util.properties;

import org.apache.log4j.Logger;
import org.mywap.util.StringTools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * 
 * 请求和并发管理类
 * 
 * @author sKF75956
 * @version [版本号, 2013年12月11日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ServerProperties
{
    private static Properties prop = new Properties();
    
    /** 调试日志 */
    private static Logger logger = Logger.getLogger(ServerProperties.class);
    
    static
    {
        BufferedInputStream is = null;
        try
        {
            is = (BufferedInputStream) ClassLoader.getSystemResourceAsStream("dbpool.properties");
                    //this.getClass().getResourceAsStream("dbpool.properties");//这个在实例方法中使用 非静态方法
            prop.load(is);
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        finally
        {
            if (null != is)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    logger.error("close stream has exception", e);
                }
            }
        }
    }
    
    /**
     * 如果取到该key的value，返回key对应的value <br/>
     * 如果没有配置该key，则取默认defaultValue
     * 
     * @param key
     * @param defaultValue
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getString(String key, String defaultValue)
    {
        String value = "";
        try
        {
            value = (String)prop.get(key);
        }
        catch (Exception e)
        {
            logger.error("ServerProperties.getString error,key:" + key, e);
        }
        if (StringTools.isNotEmpty(value))
        {
            return value;
        }
        logger.info("key=" + key + ",useing defaultValue=" + defaultValue);
        return defaultValue;
    }
    
    /**
     * 如果取到该key的value，返回key对应的value <br/>
     * 如果没有配置该key，则取默认defaultValue
     * 
     * @param key
     * @param defaultValue
     * @return [参数说明]
     *         
     * @return int [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static int getInt(String key, int defaultValue)
    {
        
        String value = getString(key, "");
        if (StringTools.isEmpty(value))
        {
            return defaultValue;
        }
        try
        {
            return Integer.parseInt(value);
        }
        catch (Exception e)
        {
            logger.error("ServerProperties.getInt error,key:" + key, e);
            return defaultValue;
        }
    }
}
