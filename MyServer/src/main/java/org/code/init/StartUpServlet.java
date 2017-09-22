package org.code.init;

import org.apache.log4j.Logger;
import org.code.tools.properties.spring.PropertiesConfUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 对系统进行一些初始化配置
 * 
 * @author heyunliang
 * 
 */
public class StartUpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StartUpServlet.class);

    public static ApplicationContext applicationContext;

	ServletContext application;

	public void init(ServletConfig config) throws ServletException {
        log.info("init begin ... ");
        
        application = config.getServletContext();
        application.setAttribute("ctxPath", application.getContextPath());
        //获取到spring的上下文对象
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        
        String projectName = config.getServletContext().getContextPath();
        PropertiesConfUtils.getInstances().setProjectName(projectName);
        log.info("projectName = " + projectName);
        
        String webAppRootPath = config.getServletContext().getRealPath("/");
        PropertiesConfUtils.getInstances().setWebAppRootPath(webAppRootPath);
        log.info("webAppRootPath = " + webAppRootPath);
        
//        // 加载 server 接口 ActionConfig.xml 配置文件
//        IFProperties.init();
//        PortalServer.getInstance().start();
        
        log.info("init success ");
	}

	public void destroy() {
        log.info("开始停止应用");
		application.removeAttribute("ctxPath");
		super.destroy();
	}

}
