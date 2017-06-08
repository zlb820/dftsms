package org.common.RBAC.dao.exception;

/**
 * 非设计预计异常
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
public class NotExpectedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotExpectedException() {
		super("非设计预计异常");
	}

	public NotExpectedException(String msg) {
		super(msg);
	}
}
