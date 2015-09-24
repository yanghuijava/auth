package com.yanghui.auth.biz.service;

import java.util.List;

import com.yanghui.auth.biz.model.Resource;

public interface ResourceService {
	
	/**
	 * 根据父Id查询出其孩子节点
	 * @param pid
	 * @return
	 */
	public List<Resource> queryByPid(Integer pid);
	/**
	 * 保存对象
	 * @param resource
	 * @return
	 */
	public int save(Resource resource);
	/**
	 * 根据id删除记录（级联删除）
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * 获取指定用户的资源（根据资源code获取其孩子为types类型的资源）
	 * @param userId 用户ID
	 * @param parentCode 资源的父code
	 * @param types 资源的类型集合
	 * @return
	 */
	public List<Resource> getUserSubResources(Integer userId,String parentCode,List<Integer> types);
	/**
	 * 根据角色ID获取资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> getResourceByRoleId(Integer roleId);
	/**
	 * 判断当前节点是否是叶子节点
	 * @param resource
	 * @return
	 */
	public boolean isLeaf(Resource resource);
	/**
	 * 初始化sql
	 * @return
	 */
	public String initsql();
}
