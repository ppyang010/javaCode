package org.mywap.util;

import java.io.File;




import javax.servlet.http.HttpServletRequest;


/**
 * http下载操作类
 * 
 * @version [版本号, 2013-12-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HttpUtil
{
    
    /**
     * 获取内容文件的本地保存路径 格式为: objectId+当前系统时间取成2005-10-11的格式，
     * 形成file/objectId/2005-10-11/的目录路径
     * 
     * @param objectId String 内容ID
     * @param timeStamp 时间戳
     * @return String 本地保存路径
     */
    public static String getFilePath1(String path, String objectId,
        String timeStamp)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append('/');
        sb.append(path);
        sb.append('/');
        sb.append(getPathPref(objectId));
        sb.append("/");
        sb.append(objectId);
        if (null != timeStamp && !"".equals(timeStamp.trim()))
        {
            sb.append('/');
            sb.append(timeStamp);
        }
        sb.append('/');
        return sb.toString();
    }
    
    /**
     * 获取文件的本地保存路径 格式为: path/objectId/yyyymmddhhMMss/
     * 
     * @param path
     * @param objectId
     * @param timeStamp
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getFilePath2(String path, String objectId,
        String timeStamp)
    {
        StringBuffer sb = new StringBuffer(128);
        sb.append('/');
        sb.append(path);
        sb.append('/');
        sb.append(objectId);
        if (null != timeStamp && !"".equals(timeStamp.trim()))
        {
            sb.append('/');
            sb.append(timeStamp);
        }
        sb.append('/');
        return sb.toString();
    }
    
    private static String getPathPref(String objectId)
    {
        return String.valueOf((toLong(objectId, 10000) % 10000) + 10000)
            .substring(1, 5);
    }
    
    private static Long toLong(String s, long def)
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
     * 通过传入的文件路径（包含完整路径和文件名）获取文件名
     * 
     * @param filePath String 包含完整的文件路径和文件名字符串
     * @return String 文件名
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getFileName(String filePath)
    {
        // 替换路径中的"//";
        filePath = filePath.replace('\\', '/');
        int pos = filePath.lastIndexOf("/");
        return filePath.substring(pos + 1);
    }
    
    /**
     * 判断文件是否存在
     * 
     * @param filePath
     * @param fileName
     * @return [参数说明]
     */
//    public static boolean isFileExist(String filePath, String fileName)
//    {
//        String homeDir = SystemConfig.getInstance().getWwwroot();
//        String coverFile = homeDir + filePath + fileName;
//        File file = new File(coverFile);
//        return file.exists();
//    }
    
    
    /**
     * 获取ip地址
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request){
    	 String ip = request.getHeader("x-forwarded-for"); 
         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
             ip = request.getHeader("Proxy-Client-IP"); 
         } 
         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
             ip = request.getHeader("WL-Proxy-Client-IP"); 
         } 
         if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
             ip = request.getRemoteAddr(); 
         } 
         return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip; 
    }
}
