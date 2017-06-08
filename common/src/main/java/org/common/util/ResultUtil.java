package org.common.util;

public class ResultUtil<T> {
	public Result<T> saveResult(String code, String msg, T data) {
		Result<T> result = new Result<>();
		result.setCode(code);
		result.setMessage(msg);
		result.setData(data);
		return result;
	}

	public static ResultSimple saveResult(String code, String msg) {
		ResultSimple result = new ResultSimple();
		result.setCode(code);
		result.setMessage(msg);
		return result;
	}
}
