package org.common.RBAC.dao.exception;

public class PasswordWrong extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PasswordWrong() {
		super();
	}

	public PasswordWrong(String msg) {
		super(msg);
	}
}
