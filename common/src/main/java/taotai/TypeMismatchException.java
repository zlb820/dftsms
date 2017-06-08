package taotai;
/**
 * 类型不匹配异常
 * @author fxb fanxiaobin.fxb@qq.com
 */
@Deprecated
public class TypeMismatchException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public TypeMismatchException() {
		super("类型不匹配异常");
	}
}
