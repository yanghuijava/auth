package com.yanghui.auth.biz.model;

import java.io.Serializable;

public class Role extends BaseObject implements Serializable{
	private static final long serialVersionUID = -7992187629294041190L;
	
    private String code;

    private String name;

    private String description;
    
    public Role(){
    	
    }
    
    public Role(Integer id){
    	this.setId(id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}