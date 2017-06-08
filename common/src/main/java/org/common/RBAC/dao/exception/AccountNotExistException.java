package org.common.RBAC.dao.exception;

public class AccountNotExistException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AccountNotExistException() {
		super();
	}

	public AccountNotExistException(String msg) {
		super(msg);
	}
}
