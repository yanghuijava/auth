package com.yanghui.auth.integration.dao;

import com.yanghui.auth.biz.model.Organization;

public interface OrganizationMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
}