package com.yanghui.auth.web.utils;

import java.util.ArrayList;
import java.util.List;

public class ResourceTypeUtil {
	
	public static List<Integer> convert(String type){
		if(type == null || type.length() == 0) {
			return null;
		}
		List<Integer> types = new ArrayList<Integer>();
		for(int i=0;i<type.length();i++){
			switch (type.charAt(i)) {
			case 'm':
				types.add(1);
				break;
			case 'p':
				types.add(2);
				break;
			case 'r':
				types.add(0);
				break;
			case 'b':
				types.add(3);
				break;
			case 'a':
				types.add(4);
				break;
			}
		}
		return types;
	}
}
