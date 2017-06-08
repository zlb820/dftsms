package dftsms.web.fxb.action.base;

import org.common.RBAC.service.AccountManager;
import org.common.miaodiyun.IdentifyCode.SendIdentifyCodeManager;

import com.opensymphony.xwork2.ActionSupport;

public class IdentifyCodeBaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	protected SendIdentifyCodeManager sendIdentifyCodeManager;
	public AccountManager accountManager;

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	public SendIdentifyCodeManager getSendIdentifyCodeManager() {
		return sendIdentifyCodeManager;
	}

	public void setSendIdentifyCodeManager(SendIdentifyCodeManager sendIdentifyCodeManager) {
		this.sendIdentifyCodeManager = sendIdentifyCodeManager;
	}
}
