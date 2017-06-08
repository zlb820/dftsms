package taotai;

public class AccountAlreadyExist extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AccountAlreadyExist() {
		super("账号已存在");
	}
	public AccountAlreadyExist(String AccountID) {
		super(AccountID+"账号已存在");
	}
}
