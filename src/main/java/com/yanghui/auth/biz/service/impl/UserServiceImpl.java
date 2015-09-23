package com.yanghui.auth.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanghui.auth.biz.model.Role;
import com.yanghui.auth.biz.model.User;
import com.yanghui.auth.biz.model.UserRole;
import com.yanghui.auth.biz.service.UserService;
import com.yanghui.auth.biz.utils.MD5Util;
import com.yanghui.auth.integration.dao.ResourceMapper;
import com.yanghui.auth.integration.dao.UserMapper;
import com.yanghui.auth.web.vo.PageResult;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int save(User user) {
		if(user == null) {
			return 0;
		}
		if(user.getId() == null) {
			if(user.getAccount() == null || "".equals(user.getAccount())) {
				throw new RuntimeException("登录账号不能为空！");
			}
			User find = this.userMapper.selectByAccount(user.getAccount());
			if(find != null) {
				throw new RuntimeException("登录账号不能重复！");
			}
			user.setPassword(MD5Util.encryption(user.getAccount()));
			int result = this.userMapper.insert(user);
			if(user.getRoleList() != null && user.getRoleList().size() > 0) {
				for(Role role : user.getRoleList()){
					UserRole ur = new UserRole();
					ur.setUserId(user.getId());
					ur.setRoleId(role.getId());
					this.userMapper.insertUserRole(ur);
				}
			}
			return result;
		}else {
			user.setAccount(null);
			this.userMapper.deleteUserRole(user.getId());
			if(user.getRoleList() != null && user.getRoleList().size() > 0) {
				for(Role role : user.getRoleList()){
					UserRole ur = new UserRole();
					ur.setUserId(user.getId());
					ur.setRoleId(role.getId());
					this.userMapper.insertUserRole(ur);
				}
			}
			return this.userMapper.update(user);
		}
	}

	@Override
	public User checkUser(String account, String password) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("account", account);
		param.put("password", MD5Util.encryption(password));
		List<User> findUserList = this.userMapper.selectAll(param);
		if(findUserList.size() >=1) {
			User find = findUserList.get(0);
			find.setResourceList(this.resourceMapper.getUserAllResource(find.getId()));
			return find;
		}
		return null;
	}

	@Override
	public PageResult<User> getPage(Map<String, Object> map) {
		PageResult<User> pageResult = new PageResult<User>();
		List<User> userList = this.userMapper.getPage(map);
		for(User u : userList){
			List<UserRole> urList = this.userMapper.selectRoleByUserId(u.getId());
			StringBuffer roleIds = new StringBuffer();
			for(UserRole ur : urList){
				roleIds.append(ur.getRoleId()).append(",");
			}
			if(roleIds.length() > 0){
				roleIds.deleteCharAt(roleIds.length() - 1);
				u.setRoleIds(roleIds.toString());
			}
		}
		pageResult.setRows(userList);
		pageResult.setTotal(this.userMapper.getPageCount(map));
		return pageResult;
	}

	@Override
	public User getById(Integer id) {
		return this.userMapper.selectByKey(id);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delete(Integer id) {
		User find = this.userMapper.selectByKey(id);
		if("admin".equals(find.getAccount())) {
			throw new RuntimeException("系统管理员不能删除！");
		}
		this.userMapper.deleteUserRole(id);
		this.userMapper.delete(id);
	}
}
