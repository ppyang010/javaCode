package org.code.server.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.code.server.model.User;
import org.code.server.service.IUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")//多例
@RequestMapping("/user")
public class UserController {
	
		@Resource  
	    private IUserService userService;
		
		
		
		@RequestMapping("/show")
		public String userMethod(HttpServletRequest request,Model model) {
			int userId = Integer.parseInt(request.getParameter("id"));  
	        User user = this.userService.getUserById(userId);  
	        model.addAttribute("user", user);  
			return "showUser";
		}
}
