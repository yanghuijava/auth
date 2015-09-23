package com.yanghui.auth.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanghui.auth.biz.model.Role;
import com.yanghui.auth.biz.model.User;
import com.yanghui.auth.biz.service.UserService;
import com.yanghui.auth.dto.QueryMap;
import com.yanghui.auth.web.vo.PageResult;
import com.yanghui.auth.web.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource(name="userServiceImpl")
	private UserService userService;
	
	@RequestMapping("/listUI.html")
	public String listUI(){
		return "system/user_listUI";
	}
	@RequestMapping("/list.json")
	@ResponseBody
	public PageResult<User> list(QueryMap map){
		PageResult<User> pageResult = this.userService.getPage(map.getMap());
		return pageResult;
	}
	
	@RequestMapping("/save.json")
	@ResponseBody
	public UserVo save(UserVo userVo){
		try {
			User user = new User();
			user.setOrgId(userVo.getOrgId());
			user.setAccount(userVo.getAccount());
			user.setName(userVo.getName());
			user.setTelephone(userVo.getTelephone());
			user.setMobile(userVo.getMobile());
			user.setEmail(userVo.getEmail());
			if(userVo.getRoleIds() != null && userVo.getRoleIds().length() > 0) {
				String[] roleIdArr = userVo.getRoleIds().split(",");
				List<Role> roleList = new ArrayList<Role>();
				for(String roleId : roleIdArr){
					Role role = new Role(Integer.valueOf(roleId));
					roleList.add(role);
				}
				user.setRoleList(roleList);
			}
			this.userService.save(user);
			userVo.setId(user.getId());
			userVo.setCreateTime(user.getCurrentTime());
			userVo.setUpdateTime(user.getCurrentTime());
			userVo.setSuccess(true);
			return userVo;
		} catch (Exception e) {
			e.printStackTrace();
			userVo.setSuccess(false);
			userVo.setMessage(e.getMessage());
			return userVo;
		}
	}
	
	@RequestMapping("/update.json")
	@ResponseBody
	public UserVo update(UserVo userVo){
		try {
			User user = this.userService.getById(userVo.getId());
			user.setOrgId(userVo.getOrgId());
			user.setName(userVo.getName());
			user.setTelephone(userVo.getTelephone());
			user.setMobile(userVo.getMobile());
			user.setEmail(userVo.getEmail());
			if(userVo.getRoleIds() != null && userVo.getRoleIds().length() > 0) {
				String[] roleIdArr = userVo.getRoleIds().split(",");
				List<Role> roleList = new ArrayList<Role>();
				for(String roleId : roleIdArr){
					Role role = new Role(Integer.valueOf(roleId));
					roleList.add(role);
				}
				user.setRoleList(roleList);
			}
			this.userService.save(user);
			userVo.setUpdateTime(user.getCurrentTime());
			userVo.setSuccess(true);
			return userVo;
		} catch (Exception e) {
			e.printStackTrace();
			userVo.setSuccess(false);
			userVo.setMessage(e.getMessage());
			return userVo;
		}
	}
	@RequestMapping("/delete.json")
	@ResponseBody
	public UserVo delete(UserVo userVo){
		try {
			this.userService.delete(userVo.getId());
			userVo.setSuccess(true);
			return userVo;
		} catch (Exception e) {
			e.printStackTrace();
			userVo.setSuccess(false);
			userVo.setMessage(e.getMessage());
			return userVo;
		}
	}
}
