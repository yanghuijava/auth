package com.yanghui.auth.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yanghui.auth.biz.model.User;
import com.yanghui.auth.biz.service.UserService;
import com.yanghui.auth.web.vo.UserVo;

@Controller
@RequestMapping("/login")
public class loginController {
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@RequestMapping("/in.html")
	public String in(Model model,UserVo userVo,HttpServletRequest request){
		if(userVo.getAccount() == null || "".equals(userVo.getAccount().trim())) {
			model.addAttribute("errorMsg", "登入账号不能为空！");
			model.addAttribute("targerUrl", userVo.getTargerUrl());
			return "login";
		}
		if(userVo.getPassword() == null || "".equals(userVo.getPassword().trim())){
			model.addAttribute("errorMsg", "密码不能为空！");
			model.addAttribute("targerUrl", userVo.getTargerUrl());
			return "login";
		}
		User user = this.userService.checkUser(userVo.getAccount(), userVo.getPassword());
		if(user == null) {
			model.addAttribute("errorMsg", "账号或者密码错误！");
			model.addAttribute("targerUrl", userVo.getTargerUrl());
			return "login";
		}
		
		request.getSession().setAttribute("user", user);
		if(userVo.getTargerUrl() != null && !"".equals(userVo.getTargerUrl())) {
			return "redirect:" + userVo.getTargerUrl();
		}
		return "redirect:/index.html";
	}
	
	@RequestMapping("/out.html")
	public String out(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		return "redirect:/login.html";
	}
}
