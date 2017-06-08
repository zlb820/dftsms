package taotai;

public class InfoFormatException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public InfoFormatException() {
		super("信息格式异常");
	}
	public InfoFormatException(String msg) {
		super(msg+"—格式异常");
	}
}
