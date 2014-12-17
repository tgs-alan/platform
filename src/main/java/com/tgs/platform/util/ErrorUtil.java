package com.tgs.platform.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.codehaus.jettison.json.JSONObject;


public class ErrorUtil {
	
	Properties config;
	
	/*
	public ErrorUtil() {
		super();
		
		try {
			config = new Properties();
			FileInputStream in = new FileInputStream("configs/config.properties");
			config.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	public JSONObject getJSONErrorObject(String errorCode, String errorMessage){
		JSONObject obj = new JSONObject();
		
		try {
			obj.put("result_code"   , errorCode   );
			obj.put("result_message", errorMessage);
		} catch (Exception e) {
			e.printStackTrace();;
		}
		
		return obj;
	}
}

