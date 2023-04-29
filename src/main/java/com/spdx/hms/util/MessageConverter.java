package com.spdx.hms.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageConverter {

	private static Gson gson = null;
	
	private static Gson getGsonObject(){
		if(gson == null){
			gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		}
		return gson;
	}
	public static String convertObjectToJson(Object deviceObj){
		return getGsonObject().toJson(deviceObj);
	}
	
	
	
	
	
	
}
