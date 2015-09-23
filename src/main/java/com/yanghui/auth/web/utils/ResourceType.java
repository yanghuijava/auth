package com.yanghui.auth.web.utils;

public enum ResourceType {
	
	RESOURCE(0,"icon-resource"),
	PAGE(2,"icon-page"),
	AREA(4,"icon-area"),
	BUTTON(3,"icon-button"),
	MENU(1,"icon-menu");
	
	private int type;
	private String icon;
	
	private ResourceType(int type,String icon){
		this.type = type;
		this.icon = icon;
	}

	public static String getIcon(int type) {
		for(ResourceType rt : ResourceType.values()) {
			if(type == rt.getType()) {
				return rt.getIcon();
			}
		}
		return null;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
