package com.yanghui.auth.biz.model;

import java.io.Serializable;

public class UseRole implements Serializable {
	private static final long serialVersionUID = -6510200064480503115L;
	private Integer userId;
	private Integer RoleId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRoleId() {
		return RoleId;
	}
	public void setRoleId(Integer roleId) {
		RoleId = roleId;
	}
}
