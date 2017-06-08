package cn.zlb.exception;
/**
 * comment exceptino
 * @author Bingo
 *
 */
public class CommentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommentNotFoundException() {
		super();
	}

	public CommentNotFoundException(String message) {
		super(message);
	}

}
