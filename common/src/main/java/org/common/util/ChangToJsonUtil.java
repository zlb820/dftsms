package org.common.util;

import com.google.gson.Gson;

public class ChangToJsonUtil {
	private static Gson gson = new Gson();

	/**
	 * 对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	/**
	 * json字符串转成对象
	 * 
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String str, Class<T> type) {
		return gson.fromJson(str, type);
	}
}
