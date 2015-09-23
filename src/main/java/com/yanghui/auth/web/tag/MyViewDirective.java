package com.yanghui.auth.web.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yanghui.auth.biz.model.Resource;
import com.yanghui.auth.biz.model.User;
import com.yanghui.auth.biz.service.ResourceService;
import com.yanghui.auth.web.utils.ResourceTypeUtil;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class MyViewDirective implements TemplateDirectiveModel {
	
	@javax.annotation.Resource(name="resourceServiceImpl")
	private ResourceService resourceService;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,TemplateDirectiveBody body) throws TemplateException, IOException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		User user = request.getSession().getAttribute("user") == null ? new User() :(User)request.getSession().getAttribute("user");
		if(!params.isEmpty()) {
			StringBuffer bodyText = new StringBuffer();
			String code = null;
			String type = null;
			String id = ((SimpleScalar) params.get("id")).getAsString();
			if(params.get("code") != null){
				code = ((SimpleScalar) params.get("code")).getAsString();
			}else {
				throw new RuntimeException("code is not empty!");
			}
			if(params.get("type") != null) {
				type = ((SimpleScalar) params.get("type")).getAsString();
			}else {
				throw new RuntimeException("type is not empty!");
			}
			if(id == null){
				bodyText.append("<ul class=\"easyui-tree\">");
			}else {
				bodyText.append("<ul id=\"").append(id).append("\"").append(" class=\"easyui-tree\">");
			}
			List<Resource> resourceList = null;
			if("admin".equals(user.getAccount())){
				resourceList = this.resourceService.getUserSubResources(null, code, ResourceTypeUtil.convert(type));
				digui(resourceList, bodyText, null, type);
			}else {
				resourceList = this.resourceService.getUserSubResources(user.getId(), code, ResourceTypeUtil.convert(type));
				digui(resourceList, bodyText, user.getId(), type);
			}
			bodyText.append("</ul>");
			System.out.println(bodyText);
			env.getOut().write(bodyText.toString());
		}else {
			throw new RuntimeException("params is not empty!");
		}
	}
	
	private void digui(List<Resource> resourceList,StringBuffer bodyText,Integer userId,String type){
		for(Resource res : resourceList){
			List<Resource> subResourceList = this.resourceService.getUserSubResources(userId, res.getCode(), ResourceTypeUtil.convert(type));
			if(res.getType() == 2) {
				bodyText.append("<li><span><a turl=\"").append(res.getAction()).append("\">")
				.append(res.getName()).append("</a></span></li>");
			}else if(subResourceList.size() == 0) {
				bodyText.append("<li><span>").append(res.getName()).append("</span></li>");
			}else {
				bodyText.append("<li><span>").append(res.getName()).append("</span><ul>");
				digui(subResourceList, bodyText, userId, type);
				bodyText.append("</ul> </li>");
			}
		}
	}
}
