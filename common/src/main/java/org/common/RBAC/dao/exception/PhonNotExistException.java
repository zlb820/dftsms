package org.common.RBAC.dao.exception;

public class PhonNotExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public PhonNotExistException() {
		super();
	}
	public PhonNotExistException(String msg) {
		super(msg);
	}
}
