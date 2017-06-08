package org.common.RBAC.dao.exception;

public class EmailNotExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public EmailNotExistException() {
		super();
	}
	public EmailNotExistException(String msg) {
		super(msg);
	}
}
