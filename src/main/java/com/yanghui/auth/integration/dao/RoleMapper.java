package com.yanghui.auth.integration.dao;

import java.util.List;
import java.util.Map;

import com.yanghui.auth.biz.model.Role;
import com.yanghui.auth.biz.model.RoleResource;

public interface RoleMapper {
    int delete(Integer id);

    int insert(Role role);

    Role selectByKey(Integer id);

    int update(Role role);
    
    List<Role> getAll(Map<String,Object> param);
    
    int deleteRoleResource(Integer roleId);
    
    int insertRoleResource(RoleResource rr);
    
}