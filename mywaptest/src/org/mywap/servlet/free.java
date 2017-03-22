package org.mywap.servlet;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class free
 */
@WebServlet("/free")
public class free extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public free() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
        response.setCharacterEncoding("UTF-8"); 
		//获取模版文件路径
        String templatePath = getServletContext().getRealPath("/template");
        
        //设置模板引擎配置信息
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("utf-8");
        //加载模版文件
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        //设置对象包装器
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        
        //获取一个模版文件对应的实例
        Template template = cfg.getTemplate("test.ftl");
        
        //创建数据模型
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("user", "哈哈");
        paramMap.put("age", 24);
        paramMap.put("birth", 1989);
     
        //将数据输出到浏览器中显示
        Writer writer = response.getWriter();
        try {
            template.process(paramMap, writer);
        } catch (TemplateException e) {
            System.out.println(e.getMessage());
        } finally {
            writer.close();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
