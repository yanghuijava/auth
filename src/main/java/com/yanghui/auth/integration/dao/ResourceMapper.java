package com.yanghui.auth.integration.dao;

import java.util.List;
import java.util.Map;

import com.yanghui.auth.biz.model.Resource;

public interface ResourceMapper {
	
    int delete(Integer id);

    int insert(Resource record);

    Resource selectByKey(Integer id);

    int update(Resource resource);
    
    List<Resource> selectAll(Map<String,Object> param);
    
    List<Resource> selectUserSubs(Map<String,Object> param);
    
	List<Resource> getUserAllResource(int userId);
	
	List<Resource> getRoleAllResource(Integer roleId);
}