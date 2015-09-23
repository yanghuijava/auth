package com.yanghui.auth.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanghui.auth.biz.model.Role;
import com.yanghui.auth.biz.model.RoleResource;
import com.yanghui.auth.biz.service.RoleService;
import com.yanghui.auth.integration.dao.RoleMapper;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> query(Map<String, Object> param) {
		return this.roleMapper.getAll(param);
	}

	@Override
	public int save(Role role) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", role.getName());
		List<Role> findRoleList = this.roleMapper.getAll(param);
		if(findRoleList != null && findRoleList.size() > 0){
			throw new RuntimeException("角色名称不能重复！");
		}
		param.clear();
		param.put("code", role.getCode());
		findRoleList = this.roleMapper.getAll(param);
		if(findRoleList != null && findRoleList.size() > 0){
			throw new RuntimeException("角色编码不能重复！");
		}
		if(role.getId() == null) {
			return this.roleMapper.insert(role);
		}
		return this.roleMapper.update(role);
	}

	@Override
	public int delete(int id) {
		return this.roleMapper.delete(id);
	}

	@Override
	public Role queryById(int id) {
		return this.roleMapper.selectByKey(id);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveRoleResource(Integer roleId, String resIds) throws Exception{
		if(roleId != null){
			this.roleMapper.deleteRoleResource(roleId);
			if(resIds != null) {
				String[] resIdArr = resIds.split(",");
				this.roleMapper.deleteRoleResource(roleId);
				for(String resId : resIdArr){
					if(Integer.valueOf(resId) == 0) {
						continue;
					}
					RoleResource rr = new RoleResource();
					rr.setRoleId(roleId);
					rr.setResId(Integer.valueOf(resId));
					this.roleMapper.insertRoleResource(rr);
				}
			}
		}else {
			throw new RuntimeException("角色ID不能为空！");
		}
	}
}
