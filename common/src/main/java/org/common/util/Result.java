package org.common.util;

public class Result<T> {
	private String code;
	private String message;
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void save(String code, String msg, T data) {
		this.code = code;
		this.message = msg;
		this.data = data;
	}
}
