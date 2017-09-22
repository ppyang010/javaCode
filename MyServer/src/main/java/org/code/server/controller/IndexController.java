package org.code.server.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.code.server.model.Example;
import org.code.server.model.User;
import org.code.server.service.IUserService;
import org.code.tools.properties.spring.PropertiesConfUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

@Controller
@Scope("prototype")//多例
@RequestMapping("/")
public class IndexController {
	
		@Resource  
	    private IUserService userService;
		
		
		@RequestMapping("/index")
		public String indexMethod(Model model) {
			return "index";
		}
		@RequestMapping("/start")
		public String startMethod(Model model) {
			return "start";
		}
		@RequestMapping("/user")
		public String userMethod(HttpServletRequest request,Model model) {
	        User user = this.userService.getUserById(3);  
	        model.addAttribute("user", user);  
			return "showUser";
		}
		
		@RequestMapping("/freeUser")
		public String freeUserMethod(HttpServletRequest request,Model model) {
			int userId = Integer.parseInt(request.getParameter("id"));  
	        User user = this.userService.getUserById(userId);  
	        model.addAttribute("user", user);  
			return "test";
		}
		
		@RequestMapping("/hello")
		public String hello(@RequestBody Example exp,HttpServletRequest request) {
			System.out.println("hello");
			//System.out.println(JSON.toJSONString(request));
			System.out.println(request.getParameter("fitet[ServerName]"));
			System.out.println(exp.getFilter().getServerName());
			return null;
		}

	@RequestMapping("/baba")
	public String baba(HttpServletRequest request) {
		System.out.println("baba");
		String tem=PropertiesConfUtils.getInstances().getValueByKey("param.test","2");
		System.out.println(tem);
		return null;
	}
}
