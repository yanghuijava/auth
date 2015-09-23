package com.yanghui.auth.biz.service;

import java.util.List;
import java.util.Map;

import com.yanghui.auth.biz.model.Role;

public interface RoleService {
	
	public List<Role> query(Map<String,Object> param);
	
	public int save(Role role);
	
	public int delete(int id);
	
	public Role queryById(int id);
	/**
	 * 保存角色的资源
	 * @param roleId 角色ID
	 * @param resIds 资源id组合,以逗号隔开（look as:1,2,3.....）
	 */
	public void saveRoleResource(Integer roleId,String resIds) throws Exception;

}
