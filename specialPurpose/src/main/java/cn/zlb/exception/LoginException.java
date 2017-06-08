package cn.zlb.exception;
/**
 * 自定义的Exception
 * 登录 Exception
 * @author Bingo
 *
 */
public class LoginException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginException() {
		super();
	}

	public LoginException(String message ) {
		super(message );
	}
	
	

}
