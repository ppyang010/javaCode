package org.mywap.test;

import java.util.HashMap;
import java.util.Map;

import org.mywap.util.FreemarkerUtil;

public class FreeTest {
	public static void main(String[] args) {
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("username", "哈哈");//在ftl中要赋值的变量
		
		
		FreemarkerUtil util = new FreemarkerUtil();
		//util.fprint("001.jsp", root, "0001.jsp");
		
//		util.fprint("01.ftl", root, "01.html");
//		util.fprint("02.ftl", root, "02.html");
//		util.fprint("03.ftl", root, "03.html");
//		util.fprint("04.ftl", root, "04.html");
//		util.fprint("05.ftl", root, "05.html");
//		util.fprint("06.ftl", root, "06.html");	
		
		util.print("test.ftl", root);
	}
}
