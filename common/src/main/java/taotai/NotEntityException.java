package taotai;

/**
 * 非实体类异常
 * 
 * @author fxb fanxiaobin.fxb@qq.com
 */
public class NotEntityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotEntityException() {
	}

	public NotEntityException(String msg) {
		super(msg);
	}
}
