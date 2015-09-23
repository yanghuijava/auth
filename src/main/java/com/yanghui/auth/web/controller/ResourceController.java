package com.yanghui.auth.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanghui.auth.biz.model.Resource;
import com.yanghui.auth.biz.service.ResourceService;
import com.yanghui.auth.biz.service.RoleService;
import com.yanghui.auth.dto.Message;
import com.yanghui.auth.dto.QueryMap;
import com.yanghui.auth.dto.TreeDto;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	
	@javax.annotation.Resource(name="resourceServiceImpl")
	private ResourceService resourceService;
	@javax.annotation.Resource(name="roleServiceImpl")
	private RoleService roleService;

	@RequestMapping("/queryRoleResource.json")
	@ResponseBody
	public List<TreeDto> queryRoleResource(QueryMap map){
		List<TreeDto> treeList = new ArrayList<TreeDto>();
		TreeDto td = new TreeDto();
		td.setId(0);
		td.setText("系统资源");
		td.setState("open");
		treeList.add(td);
		if(map.getMap().get("roleId") != null) {
			List<Resource> hasResource = this.resourceService.getResourceByRoleId(Integer.parseInt(map.getMap().get("roleId").toString()));
			digui(td,hasResource);
		}else {
			digui(td,null);
		}
		return treeList;
	}
	
	private void digui(TreeDto td,List<Resource> hasResource){
		List<TreeDto> treeChildrens = new ArrayList<TreeDto>();
		List<Resource> findResourceChildrens = this.resourceService.queryByPid(td.getId());
		if(findResourceChildrens.size() > 0) {
			for(Resource res : findResourceChildrens) {
				TreeDto tdChild = new TreeDto();
				tdChild.setId(res.getId());
				tdChild.setText(res.getName());
				tdChild.setState("open");
				if(hasResource != null && hasResource.contains(res) /*&& this.resourceService.isLeaf(res)*/) {
					tdChild.setChecked(true);
				}
				treeChildrens.add(tdChild);
				digui(tdChild,hasResource);
			}
			td.setChildren(treeChildrens);
		}
	}
	@RequestMapping("/saveRoleResource.json")
	@ResponseBody
	public Message saveRoleResource(QueryMap map){
		try {
			Integer roleId = map.getMap().get("roleId") == null ? null : Integer.parseInt(map.getMap().get("roleId").toString());
			String resIds = map.getMap().get("resIds") == null ? null : map.getMap().get("resIds").toString();
			this.roleService.saveRoleResource(roleId, resIds);
			return new Message(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(false);
		}
	}
}
