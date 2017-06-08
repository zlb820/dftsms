package org.common.util;

public class ResultSimple {
	private String code;
	private String message;

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

	public void save(String code, String msg) {
		this.code = code;
		this.message = msg;
	}
}
