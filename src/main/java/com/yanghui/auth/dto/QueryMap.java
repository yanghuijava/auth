package com.yanghui.auth.dto;

import java.util.HashMap;
import java.util.Map;

public class QueryMap {
	
private Map<String, Object> map = new HashMap<String, Object>();
	
	public void put(String key, Object value) {
		map.put(key, value);
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

}
