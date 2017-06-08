package dftsms.web.fxb.action;

import org.common.util.ResultSimple;

import com.opensymphony.xwork2.Action;
import dftsms.web.fxb.action.base.AccountManagerBaseAction;
import dftsms.web.fxb.util.UtilOfFxb;

public class DeleteAccountAction extends AccountManagerBaseAction {
	private static final long serialVersionUID = 1L;
	String deleteAccountId;
	String deleteAccountName;
	ResultSimple deleteByIDResult;
	ResultSimple deleteByNameResult;

	public String deleteAccountByID() {
		deleteByIDResult = UtilOfFxb.checkParameterIsNull(deleteAccountId);
		if ("xxxx01".equals(deleteByIDResult.getCode())) {
			deleteByIDResult = accountManager.deleteAccountByID(deleteAccountId);
		}
		return Action.SUCCESS;
	}

	public String deleteAccountByName() {
		deleteByNameResult = UtilOfFxb.checkParameterIsNull(deleteAccountName);
		if ("xxxx01".equals(deleteByNameResult.getCode())) {
			deleteByNameResult = accountManager.deleteAccountByName(deleteAccountName);
		}
		return Action.SUCCESS;
	}

	/************************* getter和setter方法 *************************/
	public String getDeleteAccountId() {
		return deleteAccountId;
	}

	public void setDeleteAccountId(String deleteAccountId) {
		this.deleteAccountId = deleteAccountId;
	}

	public String getDeleteAccountName() {
		return deleteAccountName;
	}

	public void setDeleteAccountName(String deleteAccountName) {
		this.deleteAccountName = deleteAccountName;
	}

	public ResultSimple getDeleteByIDResult() {
		return deleteByIDResult;
	}

	public void setDeleteByIDResult(ResultSimple deleteByIDResult) {
		this.deleteByIDResult = deleteByIDResult;
	}

	public ResultSimple getDeleteByNameResult() {
		return deleteByNameResult;
	}

	public void setDeleteByNameResult(ResultSimple deleteByNameResult) {
		this.deleteByNameResult = deleteByNameResult;
	}

}
