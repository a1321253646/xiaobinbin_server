package com.lemeng.xiaobinbin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyDebug {
	private final static Logger LOGGER =LoggerFactory.getLogger(MyDebug.class);
	public static void log(String str) {
		LOGGER.debug(str);
		
	}
	public static void log2(String str) {
		LOGGER.error(str);
		
	}
	
	
}
