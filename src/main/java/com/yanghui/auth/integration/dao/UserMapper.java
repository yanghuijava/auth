package com.yanghui.auth.integration.dao;

import java.util.List;
import java.util.Map;

import com.yanghui.auth.biz.model.User;
import com.yanghui.auth.biz.model.UserRole;

public interface UserMapper {
	
    int delete(Integer id);
    
    int deleteUserRole(Integer userId);

    int insert(User record);

    User selectByAccount(String account);
    User selectByKey(Integer id);

    int update(User record);
    
    List<User> selectAll(Map<String,Object> param);

	List<User> getPage(Map<String, Object> map);
	int getPageCount(Map<String, Object> map);

	int insertUserRole(UserRole ur);
	
	List<UserRole> selectRoleByUserId(Integer userId);
	
	
    
}