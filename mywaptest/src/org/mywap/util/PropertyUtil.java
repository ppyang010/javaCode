//package org.mywap.util;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//
//import java.io.IOException;
//import java.util.Properties;
//
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//import org.springframework.core.io.support.ResourcePatternResolver;
//
///**
// * 载入获取property文件中的配置内容
// *
// */
//public class PropertyUtil {
//
//	private static final Logger logger = Logger.getLogger(PropertyUtil.class);
//
//	private static PropertyUtil instance = null;
//
//	private static Properties properties;
//
//	static {
//		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		try {
//			properties = PropertiesLoaderUtils.loadProperties(resolver
//					.getResource("classpath:server.properties"));
//		} catch (IOException e) {
//			logger.error("load server.properties  error!", e);
//		}
//	}
//
//	private PropertyUtil() {
//	}
//
//	public static PropertyUtil getInstance() {
//		if (instance == null) {
//			instance = new PropertyUtil();
//		}
//		return instance;
//	}
//
//	public String get(String key, String defaultValue) {
//
//		String value = null;
//
//		if (null != properties) {
//			value = properties.getProperty(StringUtils.trim(key), defaultValue);
//			if (StringUtils.isEmpty(value)) {
//				value = defaultValue;
//				logger.error("Can not get config by key:" + key
//						+ ",return default value:" + defaultValue);
//			}
//		} else {
//			logger.error("Can not get config by key:" + key
//					+ ",return default value:" + defaultValue);
//			value = defaultValue;
//		}
//		return value;
//	}
//
//	public String get(String key) {
//
//		return this.get(key, null);
//	}
//}
