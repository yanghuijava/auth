package com.yanghui.auth.biz.service;

import java.util.Map;

import com.yanghui.auth.biz.model.User;
import com.yanghui.auth.web.vo.PageResult;

public interface UserService {
	/**
	 * 保存对象
	 * @param user
	 * @return
	 */
	public int save(User user);
	/**
	 * 根据账号和密码获取用户
	 * @param account
	 * @param password
	 * @return
	 */
	public User checkUser(String account,String password);
	/**
	 * 根据指定条件分页查询
	 * @param map
	 * @return
	 */
	public PageResult<User> getPage(Map<String, Object> map);
	
	/**
	 * 根据主键获取对象
	 * @param id
	 * @return
	 */
	public User getById(Integer id);
	/**
	 * 根据主键删除对象
	 * @param id
	 */
	public void delete(Integer id);
}
