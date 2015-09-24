package com.yanghui.auth.biz.model;

import java.util.Date;

import com.yanghui.auth.biz.utils.AppContext;


public class BaseObject {
	
	private Integer id;
	private Integer status = 0;
	private String remark;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getCurrentTime(){
		return new Date();
	}
	public Integer getNormalStatus(){
		return 0;
	}
	public String getCurrentAccount(){
		User user = AppContext.getCurrentAppUser();
		if(user != null){
			return user.getAccount();
		}
		return "development";
	}
}
