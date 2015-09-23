package com.yanghui.auth.biz.model;

import java.io.Serializable;
import java.util.List;

public class User extends BaseObject implements Serializable{
	private static final long serialVersionUID = 7083213715853386060L;

    private Integer orgId;

    private String account;

    private String password;

    private String name;

    private String telephone;

    private String mobile;

    private String email;

    private Integer type;
    
    private List<Resource> ResourceList;
    
    private List<Role> roleList;
    
    private String roleIds;
    

    public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Resource> getResourceList() {
		return ResourceList;
	}

	public void setResourceList(List<Resource> resourceList) {
		ResourceList = resourceList;
	}

	public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    
}