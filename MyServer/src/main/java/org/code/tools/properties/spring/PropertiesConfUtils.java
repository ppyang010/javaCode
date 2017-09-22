package org.code.tools.properties.spring;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.code.init.StartUpServlet;

import java.util.Properties;

public class PropertiesConfUtils
{
    
    protected static final Logger logger = Logger.getLogger(PropertiesConfUtils.class);
    
    private static PropertiesConfUtils confUtils = null;
    
    private String webAppRootPath;
    
    private Properties properties;
    
    private String projectName = "v";// 工程名字，如:"emall"
    
    private PropertiesConfUtils()
    {
        
        logger.info("begin load properties ...");
        
        // propertiesConfResources 为Spring中配置的文件
        this.properties = (Properties) StartUpServlet.applicationContext.getBean("propertiesFactoryBean");
        
        logger.info("end  load properties ...");
        
    }
    
    public static PropertiesConfUtils getInstances()
    {
        if (null == confUtils)
        {
            confUtils = new PropertiesConfUtils();
        }
        return confUtils;
    }
    
    /**
     * 从系统的配置文件中获取properties配置文件中根据key 配置文件参见String配置的文件
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public String getValueByKey(String key, String defaultValue)
    {
        String value;
        if (null != properties)
        {
            value = properties.getProperty(StringUtils.trim(key), defaultValue);
            if (StringUtils.isEmpty(value))
            {
                value = defaultValue;
                logger.error("Can not get config by key:" + key + ",return default value:" + defaultValue);
            }
        }
        else
        {
            logger.error("Can not get config by key:" + key + ",return default value:" + defaultValue);
            value = defaultValue;
        }
        
        if (StringUtils.isNotEmpty(value))
        {
            value = value.trim();
        }
        
        return value;
    }
    
    public static String getValue(String key, String defaultValue)
    {
        
        return PropertiesConfUtils.getInstances().getValueByKey(key, defaultValue);
    }
    
    public static String getValue(String key)
    {
        
        return PropertiesConfUtils.getInstances().getValueByKey(key, null);
    }
    
    public String getWebAppRootPath()
    {
        return webAppRootPath;
    }
    
    public void setWebAppRootPath(String webAppRootPath)
    {
        this.webAppRootPath = webAppRootPath;
    }
    
    /**
     * 缓存配置文件目录
     * 
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String getOscachePath()
    {
        return this.webAppRootPath + "/WEB-INF/classes/cache/oscache_config/";
    }
    
    /**
     * memcace缓存配置文件目录
     * 
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String getMemcachePath()
    {
        return this.webAppRootPath + "/WEB-INF/classes/cache/memcache_config/";
    }

    /**
     * portal配置文件目录
     * 
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String getConfigPath()
    {
        return this.webAppRootPath + "/WEB-INF/classes/";
    }
    
    public String getProjectName()
    {
        if (projectName.startsWith("/"))
        {
            projectName = projectName.replaceFirst("/", "");
        }
        if (projectName.endsWith("/"))
        {
            projectName = StringUtils.substringBeforeLast(projectName, "/");
        }
        return projectName;
    }
    
    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

}
