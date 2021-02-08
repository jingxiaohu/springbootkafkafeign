package com.xing.kafkaUtils;

import java.io.IOException;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 作用： 实际对象与json之间的转换
 * 
 * @author Administrator
 *
 */
public class ObjectMapperUtil {
	//常量对象 可以调用常量对象的方法 线程安全
	public static final ObjectMapper objectMapper = new ObjectMapper();
	//把对象转成json串，把检查时异常转换为运行时异常
	public static String toJson(Object data) {
		String json=null;
		try {
			json = objectMapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
		return json;
	}
	//把json串转换成对象
//	public static Object toObject(String json,Class cls) {
//		Object obj=null;
//		try {
//			 obj = objectMapper.readValue(json,cls);
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		return obj;
//	}
	//@SuppressWarnings("unchecked")
	public static <T>T toObject(String json,Class<T> cls) {
		T obj = null;
		try {
			 obj = objectMapper.readValue(json,cls);
		} catch (IOException e) {		
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return obj;
	}


}
