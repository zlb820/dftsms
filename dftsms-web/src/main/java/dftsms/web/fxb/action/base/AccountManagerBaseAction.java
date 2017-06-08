package dftsms.web.fxb.action.base;

import org.common.RBAC.service.AccountManager;

import com.opensymphony.xwork2.ActionSupport;

public class AccountManagerBaseAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public AccountManager accountManager;
	public AccountManager getAccountManager() {
		return accountManager;
	}
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
