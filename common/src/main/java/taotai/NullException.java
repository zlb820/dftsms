package taotai;
/**
 * NULL异常,主要是针对操作过程中传入参数以及返回值为null的情况。
 * @author fxb fanxiaobin.fxb@qq.com
 */
public class NullException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public NullException() {
		super("NULL异常");
	}
	public NullException(String msg){
		super("NULL异常:"+msg);
	}
}
