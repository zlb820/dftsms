package cn.zlb.exception;
/**
 * 目录 未找到exception
 * @author Bingo
 *
 */
public class CategoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException() {
		super();
	}

	public CategoryNotFoundException(String message) {
		super(message);
	}
	
}
