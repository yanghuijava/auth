package com.yanghui.auth.biz.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtil {
	
	private static String regEx = "[\u4e00-\u9fa5]";
	private static Pattern pat = Pattern.compile(regEx);
	
	
	public static boolean isContainsChinese(String str){
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find()){
			flg = true;
		}
		return flg;
	}
	
	public static boolean isEmpty(String str){
		if(str != null && !"".equals(str.trim())) {
			return false;
		}
		return true;
	}
	
	public static String defaultValue(String str, String defaultValue){
		return str == null ? defaultValue : str;
	}
	
	
	public static String getInvoiceNumber(String date,String invoiceNumber, String id){
		//String id = String.valueOf(AppContext.getIdGenerator().getId("com.gionee.gnerp.biz.invoiceNumber" + date));
		StringBuffer sb = new StringBuffer();
		String newid = id;
		if(id.length() < 4) {
			for (int i = 0; i < 4 - id.length(); i++) {
				newid = "0" + newid;
			}
		}
		sb.append(date).append("0").append(invoiceNumber).append(newid);
		return sb.toString();
	}
	
	public static int takeBack(int value){
		if(value > 0) {
			value = Integer.valueOf("-" + Integer.valueOf(value).toString());
		}else if(value < 0){
			value = Math.abs(value);
		}
		return value;
	}
	
	public static float takeBack(float value){
		if(value > 0) {
			value = Float.valueOf("-" + Float.valueOf(value).toString());
		}else if(value < 0){
			value = Math.abs(value);
		}
		return value;
	}
	
	public static double takeBack(double value){
		if(value > 0) {
			value = Double.valueOf("-" + Double.valueOf(value).toString());
		}else if(value < 0){
			value = Math.abs(value);
		}
		return value;
	}
	
	public static boolean indexOfNum(String content){
		Pattern pattern = Pattern.compile("^(\\d+)(.*)");
		Matcher matcher = pattern.matcher(content);
		if (matcher.matches()) {//数字开头
			return true;
		}
		return false;
	}
	
	public static String fuzzyQuery(String queryKey, String fuzzyKey){
		if(!isEmpty(queryKey)){
			if(queryKey.indexOf(fuzzyKey) != -1)
				return queryKey.replaceAll("\\" + fuzzyKey, "%");
		}
		return null;
	}
	
	public static boolean isNumeric(String str){
		if(isEmpty(str)) {
			return false;
		}
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
}
