package com.yanghui.auth.biz.model;

import java.io.Serializable;

public class RoleResource extends BaseObject implements Serializable {

	private static final long serialVersionUID = 9123088290665057859L;
	private Integer roleId;
	private Integer resId;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getResId() {
		return resId;
	}
	public void setResId(Integer resId) {
		this.resId = resId;
	}
}
