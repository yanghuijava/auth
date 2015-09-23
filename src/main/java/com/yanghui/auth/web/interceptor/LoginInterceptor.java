package com.yanghui.auth.web.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yanghui.auth.biz.model.User;
import com.yanghui.auth.biz.service.ResourceService;
import com.yanghui.auth.biz.utils.StringUtil;

/**
 * 登入拦截器
 * @author yanghui
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	 
	private List<String> unCheckUrls;
	
	@Resource(name="resourceServiceImpl")
	private ResourceService resourceService;

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		if(unCheckUrls != null && unCheckUrls.size() > 0) {
			String url = request.getServletPath();
			if(unCheckUrls.contains(url)){
				return true;
			}
		}
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			if(request.getHeader("x-requested-with") != null&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
				response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
				//response.getWriter().print("timeout");
				return false;
			}
			request.setAttribute("targerUrl",request.getServletPath());
			request.getRequestDispatcher("/login.html").forward(request, response);
			return false;
		}else {
			if("admin".equals(user.getAccount())){
				return true;
			}
			List<com.yanghui.auth.biz.model.Resource> allResList = this.resourceService.getUserSubResources(null, null, null);
			String requestUrl = request.getRequestURL().toString();
			boolean flag = false;
			for(com.yanghui.auth.biz.model.Resource r : allResList) {
				if(!StringUtil.isEmpty(r.getUrl()) && requestUrl.contains(r.getUrl())){
					flag = true;
					break;
				}
			}
			if(flag) {//如果定义了系统资源，则拦截，如果没有，则不拦截
				boolean flag1 = false;
				for(com.yanghui.auth.biz.model.Resource r : user.getResourceList()) {
					if(!StringUtil.isEmpty(r.getUrl()) && requestUrl.contains(r.getUrl())){
						flag1 = true;
						break;
					}
				}
				if(!flag1){
					request.getRequestDispatcher("/common/404.html").forward(request, response);
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {

	}

	public List<String> getUnCheckUrls() {
		return unCheckUrls;
	}

	public void setUnCheckUrls(List<String> unCheckUrls) {
		this.unCheckUrls = unCheckUrls;
	}
}
