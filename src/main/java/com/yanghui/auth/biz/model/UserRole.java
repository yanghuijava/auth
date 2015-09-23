package com.yanghui.auth.biz.model;

import java.io.Serializable;

public class UserRole extends BaseObject implements Serializable{

	private static final long serialVersionUID = 1068573316140154431L;
	
	private Integer userId;
	
	private Integer roleId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	

}
