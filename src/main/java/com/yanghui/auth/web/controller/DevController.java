package com.yanghui.auth.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yanghui.auth.biz.model.Resource;
import com.yanghui.auth.biz.service.ResourceService;
import com.yanghui.auth.dto.Message;
import com.yanghui.auth.dto.QueryMap;
import com.yanghui.auth.dto.TreeDto;
import com.yanghui.auth.web.utils.ResourceType;
import com.yanghui.auth.web.vo.ResourceVo;

@Controller
@RequestMapping("/dev")
public class DevController {
	
	@javax.annotation.Resource(name="resourceServiceImpl")
	private ResourceService  resourceService;
	
	@RequestMapping("/resourceTree.json")
	@ResponseBody
	public List<TreeDto> resourceTree(QueryMap map){
		if(map.getMap().get("id") == null) {
			List<TreeDto> tdList = new ArrayList<TreeDto>();
			TreeDto td = new TreeDto();
			td.setId(0);
			td.setText("系统资源");
			td.setIconCls(ResourceType.getIcon(0));
			td.setState("open");
			List<Resource> findResourceChildrens = this.resourceService.queryByPid(td.getId());
			List<TreeDto> treeDtoChildrens = new ArrayList<TreeDto>();
			for(Resource res : findResourceChildrens){
				TreeDto treeDtoChild = new TreeDto();
				treeDtoChild.setId(res.getId());
				if(res.getCode() != null && !"".equals(res.getCode().trim())) {
					treeDtoChild.setText(res.getName() + "<span class=\"blue\">[ " + res.getCode() + "]</span>");
				}else {
					treeDtoChild.setText(res.getName());
				}
				if(this.resourceService.queryByPid(res.getId()).size() > 0) {
					treeDtoChild.setState("closed");
				}else {
					treeDtoChild.setState("open");
				}
				treeDtoChild.setIconCls(ResourceType.getIcon(res.getType()));
				treeDtoChildrens.add(treeDtoChild);
			}
			td.setChildren(treeDtoChildrens);
			tdList.add(td);
			return tdList;
		}else {
			List<Resource> findResourceChildrens = this.resourceService.queryByPid(Integer.parseInt(map.getMap().get("id").toString()));
			List<TreeDto> treeDtoChildrens = new ArrayList<TreeDto>();
			for(Resource res : findResourceChildrens){
				TreeDto treeDtoChild = new TreeDto();
				treeDtoChild.setId(res.getId());
				if(res.getCode() != null && !"".equals(res.getCode().trim())) {
					treeDtoChild.setText(res.getName() + "<span class=\"blue\">[ " + res.getCode() + "]</span>");
				}else {
					treeDtoChild.setText(res.getName());
				}
				if(this.resourceService.queryByPid(res.getId()).size() > 0) {
					treeDtoChild.setState("closed");
				}else {
					treeDtoChild.setState("open");
				}
				treeDtoChild.setIconCls(ResourceType.getIcon(res.getType()));
				treeDtoChildrens.add(treeDtoChild);
			}
			return treeDtoChildrens;
		}
	}
	@RequestMapping("/subres.json")
	@ResponseBody
	public List<Resource> subres(QueryMap map) {
		return this.resourceService.queryByPid(Integer.valueOf(map.getMap().get("id").toString()));
	}
	
	@RequestMapping("/save_res.json")
	@ResponseBody
	public ResourceVo saveRes(ResourceVo resourceVo) {
		try {
			Resource resource = new Resource();
			resource.setPid(resourceVo.getPid());
			resource.setType(resourceVo.getType());
			resource.setName(resourceVo.getName());
			resource.setCode(resourceVo.getCode());
			resource.setAction(resourceVo.getAction());
			resource.setUrl(resourceVo.getUrl());
			resource.setIndex(resourceVo.getIndex());
			resource.setIcon(resourceVo.getIcon());
			this.resourceService.save(resource);
			resourceVo.setId(resource.getId());
			resourceVo.setSuccess(true);
			return resourceVo;
		} catch (Exception e) {
			e.printStackTrace();
			resourceVo.setSuccess(false);
			resourceVo.setMessage(e.getMessage());
			return resourceVo;
		}
	}
	
	@RequestMapping("/update_res.json")
	@ResponseBody
	public ResourceVo updateRes(ResourceVo resourceVo) {
		try {
			Resource resource = new Resource();
			resource.setId(resourceVo.getId());
			resource.setPid(resourceVo.getPid());
			resource.setType(resourceVo.getType());
			resource.setName(resourceVo.getName());
			resource.setCode(resourceVo.getCode());
			resource.setAction(resourceVo.getAction());
			resource.setUrl(resourceVo.getUrl());
			resource.setIndex(resourceVo.getIndex());
			resource.setIcon(resourceVo.getIcon());
			this.resourceService.save(resource);
			resourceVo.setSuccess(true);
			return resourceVo;
		} catch (Exception e) {
			e.printStackTrace();
			resourceVo.setSuccess(false);
			resourceVo.setMessage(e.getMessage());
			return resourceVo;
		}
	}
	
	@RequestMapping("/delete_res.json")
	@ResponseBody
	public Message deleteRes(ResourceVo resourceVo) {
		try {
			this.resourceService.delete(resourceVo.getId());
			return new Message(true);
		} catch (Exception e) {
			e.printStackTrace();
			return new Message(false);
		}
	}
}
