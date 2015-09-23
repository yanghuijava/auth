package com.yanghui.auth.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanghui.auth.biz.model.Role;
import com.yanghui.auth.biz.service.RoleService;
import com.yanghui.auth.dto.QueryMap;
import com.yanghui.auth.web.vo.RoleVo;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@Resource(name="roleServiceImpl")
	private RoleService roleService;
	
	@RequestMapping("/listUI.html")
	public String listUI(){
		return "system/role_listUI";
	}
	
	@RequestMapping("/list.json")
	@ResponseBody
	public List<Role> list(QueryMap map){
		return this.roleService.query(map.getMap());
	}
	
	@RequestMapping("/save.json")
	@ResponseBody
	public RoleVo save(RoleVo roleVo){
		try {
			Role role = new Role();
			role.setCode(roleVo.getCode());
			role.setName(roleVo.getName());
			role.setDescription(roleVo.getDescription());
			this.roleService.save(role);
			roleVo.setId(role.getId());
			roleVo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			roleVo.setSuccess(false);
			roleVo.setMessage(e.getMessage());
		}
		return roleVo;
	}
	
	@RequestMapping("/update.json")
	@ResponseBody
	public RoleVo update(RoleVo roleVo){
		try {
			Role find = this.roleService.queryById(roleVo.getId());
			find.setCode(roleVo.getCode());
			find.setName(roleVo.getName());
			find.setDescription(roleVo.getDescription());
			this.roleService.save(find);
			roleVo.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			roleVo.setSuccess(false);
			roleVo.setMessage(e.getMessage());
		}
		return roleVo;
	}
	
	@RequestMapping("/delete.json")
	@ResponseBody
	public RoleVo delete(RoleVo roleVo){
		try {
			this.roleService.delete(roleVo.getId());
			roleVo.setSuccess(true);
			return roleVo;
		} catch (Exception e) {
			e.printStackTrace();
			roleVo.setSuccess(false);
			return roleVo;
		}
	}
	
	@RequestMapping("/queryRoleNames.json")
	@ResponseBody
	public String queryRoleNames(QueryMap map){
		try {
			String roleIds = map.getMap().get("roleIds") == null ? null : map.getMap().get("roleIds").toString();
			if(roleIds != null && !"".equals(roleIds)) {
				String[] roleIdArr = roleIds.split(",");
				StringBuffer roleNames = new StringBuffer();
				for(String roleId : roleIdArr){
					Role role = this.roleService.queryById(Integer.valueOf(roleId));
					roleNames.append(role.getName()).append(",");
				}
				if(roleNames.length() > 0) {
					roleNames.deleteCharAt(roleNames.length() - 1);
				}
				return roleNames.toString();
			}else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
