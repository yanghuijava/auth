package com.yanghui.auth.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanghui.auth.biz.model.Resource;
import com.yanghui.auth.biz.service.ResourceService;
import com.yanghui.auth.integration.dao.ResourceMapper;
@Service
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<Resource> queryByPid(Integer pid) {
		if(pid == null) {
			return null;
		}
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("pid", pid);
		return this.resourceMapper.selectAll(param);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int save(Resource resource) {
		if(resource.getId() == null) {
			if(resource.getCode() != null && !"".equals(resource.getCode())) {
				validate(resource.getCode());
			}
			return this.resourceMapper.insert(resource);
		}else {
			Resource find = this.resourceMapper.selectByKey(resource.getId());
			if(resource.getCode() != null && !"".equals(resource.getCode()) && !find.getCode().equals(resource.getCode())) {
				validate(resource.getCode());
			}
			return this.resourceMapper.update(resource);
		}
	}
	
	private void validate(String code){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("code",code);
		if(this.resourceMapper.selectAll(param).size() >= 1){
			throw new RuntimeException("code can not repeat");
		}
	}

	@Override
	public void delete(Integer id) {
		recursionDelete(id);
	}
	
	private void recursionDelete(Integer id) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("pid", id);
		List<Resource> resourceList = this.resourceMapper.selectAll(param);
		if(resourceList.size() == 0) {
			this.resourceMapper.delete(id);
		}else {
			for(Resource r : resourceList){
				recursionDelete(r.getId());
				this.resourceMapper.delete(r.getId());
			}
		}
		this.resourceMapper.delete(id);
	}

	@Override
	public List<Resource> getUserSubResources(Integer userId,String parentCode, List<Integer> types) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("parentCode", parentCode);
		param.put("types", types);
		return this.resourceMapper.selectUserSubs(param);
	}

	@Override
	public List<Resource> getResourceByRoleId(Integer roleId) {
		return this.resourceMapper.getRoleAllResource(roleId);
	}

	@Override
	public boolean isLeaf(Resource resource) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("pid", resource.getId());
		List<Resource> findList = this.resourceMapper.selectAll(param);
		if(findList.size() > 0) {
			return false;
		}
		return true;
	}
}
