package org.common.RBAC.dao.exception;

public class StoreNotExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public StoreNotExistException() {
		super();
	}

	public StoreNotExistException(String msg) {
		super(msg);
	}

}
