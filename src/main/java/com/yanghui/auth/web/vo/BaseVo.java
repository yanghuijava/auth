package com.yanghui.auth.web.vo;

import java.util.Date;

import com.yanghui.auth.biz.utils.Constant;

public class BaseVo {
	
	private Integer id;
	private boolean isError;
	private String message;
	private boolean success;
	
	private Integer status = 0;
	private String remark;
	private String createBy;
	private Date createTime;
	private String updateBy;
	private Date updateTime;
	
	
	
	public boolean getIsError() {
		return isError;
	}
	public void setIsError(boolean isError) {
		this.isError = isError;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
		if(this.success) {
			this.message = Constant.HANDLE_SUCCESS;
		}else {
			this.message = Constant.HANDLE_FAIL;
		}
	}
}
