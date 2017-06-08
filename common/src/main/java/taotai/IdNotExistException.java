package taotai;

/**
 * ID不存在异常
 * @author fxb fanxiaobin.fxb@qq.com
 */
public class IdNotExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IdNotExistException() {
		super("ID不存在异常");
	}
}
