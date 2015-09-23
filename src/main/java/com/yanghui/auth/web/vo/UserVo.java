package com.yanghui.auth.web.vo;

public class UserVo extends BaseVo {
	
		private Integer orgId;

	    private String account;

	    private String password;

	    private String name;

	    private String telephone;

	    private String mobile;

	    private String email;

	    private Integer type;
	    
	    private String targerUrl;
	    
	    private String roleIds;
	    
		public String getRoleIds() {
			return roleIds;
		}

		public void setRoleIds(String roleIds) {
			this.roleIds = roleIds;
		}

		public String getTargerUrl() {
			return targerUrl;
		}

		public void setTargerUrl(String targerUrl) {
			this.targerUrl = targerUrl;
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
