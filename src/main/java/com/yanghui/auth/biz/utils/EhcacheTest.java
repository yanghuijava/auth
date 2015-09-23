package com.yanghui.auth.biz.utils;

import net.sf.ehcache.CacheManager;


public class EhcacheTest {

	public static void main(String[] args) {
		CacheManager manager = CacheManager.newInstance("src/main/resources/ehcache.xml"); 
		String[] cacheNames = manager.getCacheNames();
		System.out.println(cacheNames.length);
		for(String cn : cacheNames) {
			System.out.println(cn);
		}
	}
}
