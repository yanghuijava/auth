package com.yanghui.auth.biz.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtil {
	
	public static SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 将日期格式化成默认格式字符串，默认格式yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		return defaultDateFormat.format(date);
	}

	public static String format(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static Date parser(String text,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(text, new ParsePosition(0));
	}
	/**  
	 * 判断是否闰年
	 * @param year
	 * @return
	 */
	public static boolean IsLeapYear(int year) {
		if (year % 100 == 0 && year % 400 == 0) {
			return true;
		}else if (year % 4 == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据年和月返回一个随机的除了星期日的日期
	 * @param year
	 * @param mouth
	 * @return
	 */
	public static Date getRandomDate(int year,int mouth){
		Calendar calender = Calendar.getInstance();
		Random random = new Random();
		boolean flag = false;
		do {
			if(DateUtil.IsLeapYear(year) && mouth == 2){
				calender.set(year, mouth - 1, random.nextInt(29) + 1);
			}else if(!DateUtil.IsLeapYear(year) && mouth == 2){
				calender.set(year, mouth - 1, random.nextInt(28) + 1);
			}else {
				if(mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7
						|| mouth == 8 || mouth == 10 || mouth == 12) {
					calender.set(year, mouth - 1, random.nextInt(31) + 1);
				}else {
					calender.set(year, mouth - 1, random.nextInt(30) + 1);
				}
			}
			if(calender.get(Calendar.DAY_OF_WEEK) - 1 == 0){
				flag = true;
			}else {
				flag = false;
			}
		}while(flag);
		return calender.getTime();
	}
	
	/**
     * 获取某年某月的第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {     
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);     
        cal.set(Calendar.MONTH, month - 1);  
        cal.set(Calendar.DAY_OF_MONTH, 1);
       return defaultDateFormat.format(cal.getTime());  
    } 
    
	/**
	 * 获取某年某月的最后一天
	 * @param year
	 * @param month
	 * @return
	 */
    public static String getLastDayOfMonth(int year, int month) { 
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);     
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
       return defaultDateFormat.format(cal.getTime());
    }
    
    
    public static String getFirstDayOfMonthOfDC(int year, int month) {     
       Calendar cal = Calendar.getInstance();     
       cal.set(Calendar.YEAR, year);     
       cal.set(Calendar.MONTH, month - 1);  
       cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
       return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());  
    }   
	
    public static String getLastDayOfMonthOfDD(int year, int month) { 
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);     
        cal.set(Calendar.MONTH, month - 1);     
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));  
       return  new   SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());  
    }
}
