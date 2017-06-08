package taotai;

/**
 * 信息不全异常
 * @author fxb fanxiaobin.fxb@qq.com
 */
public class IncompleteInfoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public IncompleteInfoException() {
		super("信息不全异常");
	}
}
